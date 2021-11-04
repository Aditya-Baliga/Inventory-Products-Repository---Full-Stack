import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import {MessageService} from 'primeng/api';

import { ValidationPatternService } from 'src/app/shared/validation/validation-pattern.service';
import { Product, ProductRequest } from '../product-shared/product.model';
import { ProductService } from '../product-shared/product.service';

@Component({
  selector: 'app-product-create',
  templateUrl: './product-create.component.html',
  styleUrls: ['./product-create.component.css'],
})
export class ProductCreateComponent implements OnInit {
  productCreateForm : FormGroup;
  
  constructor(private productService: ProductService,
    private readonly formBuilder: FormBuilder,
    private readonly validationPattern: ValidationPatternService,
    private readonly messageService: MessageService,
    private router: Router) { }

  ngOnInit(): void {
    this.setProductCreateForm();
  }

  setProductCreateForm(): void {
    this.productCreateForm = this.formBuilder.group({
      'name': new FormControl(
        null,
        [
          Validators.required,
          Validators.maxLength(256),
          Validators.pattern(this.validationPattern.noHtmlTags()),
          Validators.pattern(this.validationPattern.noWhitespaceOnly())
        ],
        // this.checkOnDuplicateProductName.bind(this)
      ),
      'category': new FormControl(
        null,
        [
          Validators.required,
          Validators.maxLength(256),
          Validators.pattern(this.validationPattern.noHtmlTags()),
          Validators.pattern(this.validationPattern.noWhitespaceOnly())
        ]
      ),
      'description': new FormControl(
        null,
        [
          Validators.required,
          Validators.maxLength(1000),
          Validators.pattern(this.validationPattern.noHtmlTags())
        ]
      ),
      'units': new FormControl(
        1,
        [
          Validators.required,
          Validators.pattern(this.validationPattern.noNonIntegral()),
          Validators.pattern(this.validationPattern.noWhitespaceOnly())
        ]
      ),
    })
  }

  createProduct() {
    this.router.navigateByUrl('main/products/overview');
    const productName = this.productCreateForm.value.name;
    this.messageService.add({severity:'info', summary:'Creating product', detail:`Product ${productName} is in creation process`});
    const productCreateRequest = this.productService.transformToProductRequest(this.productCreateForm.value);
    this.productService.addProduct(productCreateRequest).subscribe(
      () => {
        this.messageService.add({severity:'success', summary:'Product creation successful', detail:`Product ${productName} created successfully`});
        if(this.router.url == 'main/products/overview') {
          this.productService.updateProductListView();
        }
      },
      (err) => {
        this.messageService.add({severity:'error', summary:'Product creation failed', detail:`Failed to create product ${productName}`});
      }
    )
    
  }


}

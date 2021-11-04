import { Component, ElementRef, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService } from 'primeng/api';

import { ValidationPatternService } from './../../shared/validation/validation-pattern.service';
import { Product } from '../product-shared/product.model';
import { ProductService } from '../product-shared/product.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
  productUpdateForm: FormGroup;
  productFormCache: any = [];
  product: Product = null;
  productId: string;
  editDataMode: boolean = false;

  constructor(private productService: ProductService,
    private readonly formBuilder: FormBuilder,
    private readonly validationPattern: ValidationPatternService,
    private readonly messageService: MessageService,
    private router: Router,
    private activeRoute: ActivatedRoute,
    public elRef: ElementRef) { }

  ngOnInit(): void {
    this.productId = this.activeRoute.snapshot.params['id'];
    if (!this.productId) {
      this.router.navigateByUrl('main/products/overview');
      this.messageService.add({ severity: 'error', summary: 'Failed to get the product', detail: `Failed to get the product from server` });
    }
    this.getProduct(this.productId);
  }

  getProduct(productId: string): void {
    this.productService.getProduct(productId).subscribe(
      (product) => {
        this.product = product;
        this.initProductUpdateForm();
      },
      () => {
        this.router.navigateByUrl('main/products/overview');
        this.messageService.add({ severity: 'error', summary: 'Failed to get the product', detail: `Failed to get the product from server` });
      })
  }

  initProductUpdateForm(): void {
    this.productUpdateForm = this.formBuilder.group({
      'name': new FormControl(
        {
          value: this.product.name ? this.product.name : '',
          disabled: true
        },
        [
          Validators.required,
          Validators.maxLength(256),
          Validators.pattern(this.validationPattern.noHtmlTags()),
          Validators.pattern(this.validationPattern.noWhitespaceOnly())
        ],
        // this.checkOnDuplicateProductName.bind(this)
      ),
      'category': new FormControl(
        {
          value: this.product.category ? this.product.category : '',
          disabled: true
        },
        [
          Validators.required,
          Validators.maxLength(256),
          Validators.pattern(this.validationPattern.noHtmlTags()),
          Validators.pattern(this.validationPattern.noWhitespaceOnly())
        ]
      ),
      'description': new FormControl(
        {
          value: this.product.description ? this.product.description : '',
          disabled: true
        },
        [
          Validators.required,
          Validators.maxLength(1000),
          Validators.pattern(this.validationPattern.noHtmlTags())
        ]
      ),
      'units': new FormControl(
        {
          value: this.product.units ? this.product.units : '',
          disabled: true
        },
        [
          Validators.required,
          Validators.pattern(this.validationPattern.noNonIntegral()),
          Validators.pattern(this.validationPattern.noWhitespaceOnly())
        ]
      ),
    })
  }

  enableEditDataMode(): void {
    this.editDataMode = true;
    this.productUpdateForm.enable();
    this.productFormCache['name'] = this.productUpdateForm.controls['name'].value;
    this.productFormCache['description'] = this.productUpdateForm.controls['description'].value;
    this.productFormCache['units'] = this.productUpdateForm.controls['units'].value;
    this.productFormCache['category'] = this.productUpdateForm.controls['category'].value;

  }

  disableEditDataMode(): void {
    this.editDataMode = false;
    this.productUpdateForm.disable();
  }

  cancelEditDataMode(): void {
    this.productUpdateForm.controls['name'].setValue(this.productFormCache['name']);
    this.productUpdateForm.controls['description'].setValue(this.productFormCache['description']);
    this.productUpdateForm.controls['units'].setValue(this.productFormCache['units']);
    this.productUpdateForm.controls['category'].setValue(this.productFormCache['category']);

    this.disableEditDataMode();
  }


  updateProduct() {
    const productName = this.productUpdateForm.value.name;
    this.messageService.add({ severity: 'info', summary: 'Product updated', detail: `Product ${productName} is in updation process` });
    const productCreateRequest = this.productService.transformToProductRequest(this.productUpdateForm.value);
    this.productService.updateProduct(this.productId, productCreateRequest).subscribe(
      () => {
        this.disableEditDataMode();
        this.messageService.add({ severity: 'success', summary: 'Product updation successful', detail: `Product ${productName} updated successfully` });
      },
      (err) => {
        this.messageService.add({ severity: 'error', summary: 'Product updation failed', detail: `Failed to update product ${productName}` });
      }
    )

  }
}
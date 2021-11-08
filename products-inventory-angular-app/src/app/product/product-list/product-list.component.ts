import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Table } from 'primeng/table';

import { Product } from '../product-shared/product.model';
import { ProductService } from '../product-shared/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  products: Product[];
  selectedProducts: Product[];
  loading: boolean = true;

  @ViewChild('dt') table: Table; 

  constructor(private productService: ProductService,
    private router: Router,
    private readonly messageService: MessageService,
    private readonly confirmationService: ConfirmationService) {
    this.productService.onUpdateProductList().subscribe((val) => {
      this.getAllProducts();
    })
  }

  ngOnInit() {
    this.getAllProducts();
  }

  private getAllProducts() {
    this.productService.getProducts().subscribe((products) => {
      this.products = products;
      this.loading = false;
    },
      () => {
        this.products = [];
        this.loading = false;
      });
  }

  onUnitsFilterChange(event: { target: { value: any; }; }) {
    const value = event.target.value;
    if (value && value.trim().length) {
      const units = parseInt(value);

      if (!isNaN(units)) {
        this.table.filter(units, 'units', 'gte');
      } else {
        this.table.filter(0, 'units', 'gte');
      }
    }
  }

  navigateToProductDetails(productId: string): void {
    this.router.navigateByUrl(`/main/products/${productId}`);
  }

  navigateToProductCreate() {
    this.router.navigateByUrl('/main/products/new');
  }

  deleteProduct(productId: string, productName: string): void {
    this.productService.deleteProduct(productId).subscribe(() => {
      this.messageService.add({ severity: 'success', summary: 'Product deletion successful', detail: `Product ${productName} deleted successfully` });
      this.getAllProducts();
    },
      () => {
        this.messageService.add({ severity: 'error', summary: 'Product deletion failed', detail: `Failed to delete product ${productName}` });
      });
  }

  // deleteSelectedProduct(): void {
  //   let count = 0;
  //   let failCount = 0;
  //   let reqLength = this.selectedProducts.length;
  //   this.selectedProducts.forEach(product => {
  //       this.productService.deleteProduct(product.id).subscribe(() => {
  //         count ++;
  //       }, () => {
  //         count++;
  //         failCount++;
  //       });
  //   });

  //   if(count == reqLength) {

  //     if(!failCount) {
  //       this.messageService.add({ severity: 'success', summary: 'Product deletion successful', detail: `Product ${count} created successfully` });
  //     }
  //     this.getAllProducts();
  //   }
  // }

  confirmProductDeletion(productId: string, productName: string): void {
    this.confirmationService.confirm({
      message: `Are you sure you want to delete the product <b>${productName}</b>  ?`,
      header: 'Confirm deletion',
      icon: 'pi pi-info-circle',
      accept: () => {
        this.deleteProduct(productId, productName);
      }
    });
  }




}
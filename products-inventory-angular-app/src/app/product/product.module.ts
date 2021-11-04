import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductRoutingModule } from './product-routing.module';
import { ProductCreateComponent } from './product-create/product-create.component';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { UilibraryModule } from '../shared/uilibrary/uilibrary.module';
import { ProductService } from './product-shared/product.service';


@NgModule({
  declarations: [
    ProductCreateComponent,
    ProductListComponent,
    ProductDetailComponent
  ],
  imports: [
    CommonModule,
    UilibraryModule,
    ProductRoutingModule
  ],
  exports: [
    ProductCreateComponent,
    ProductListComponent,
    ProductDetailComponent
  ],
  providers:[ProductService]
})
export class ProductModule { }

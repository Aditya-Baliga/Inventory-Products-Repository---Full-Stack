import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductCreateComponent } from './product-create/product-create.component';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { ProductListComponent } from './product-list/product-list.component';

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: '',
        redirectTo: 'overview',
        pathMatch:'full'
      },
      {
        path: 'overview',
        component: ProductListComponent,
      },
      {
        path: 'new',
        component: ProductCreateComponent,
      },
      {
        path: ':id',
        component: ProductDetailComponent,
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductRoutingModule { }

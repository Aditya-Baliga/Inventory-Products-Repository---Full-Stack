import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ProductCreateComponent } from '../product/product-create/product-create.component';
import { ProductDetailComponent } from '../product/product-detail/product-detail.component';
import { ProductListComponent } from '../product/product-list/product-list.component';
import { MainComponent } from './main.component';

const routes: Routes = [
  {
    path: 'login',
    component: ProductListComponent
  },
  {
    path: 'main',
    component: MainComponent,
    children: [
      {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full'
      },
      { // redirect to product list page untill dashboard page is created
        path: 'home',
        component: ProductListComponent
      },
      {
        path: 'dashboard',
        redirectTo: 'home',
        pathMatch: 'full'
      },
      {
        // lazy loading 
        path: 'products',
        loadChildren: () => import('../product/product.module').then(m => m.ProductModule)
      },
      {
        // for now move to product list page until page not found component is not created
        path: '**',
        component: ProductListComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MainRoutingModule { }

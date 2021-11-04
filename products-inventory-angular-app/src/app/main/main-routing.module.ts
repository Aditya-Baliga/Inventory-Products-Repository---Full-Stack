import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ProductCreateComponent } from '../product/product-create/product-create.component';
import { ProductDetailComponent } from '../product/product-detail/product-detail.component';
import { ProductListComponent } from '../product/product-list/product-list.component';
import { AuthService } from './authentication/auth.service';
import { LoginComponent } from './login/login.component';
import { MainComponent } from './main.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'main',
    component: MainComponent,
    children: [
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
      },
      {
        // Temporary change for demo MVP.. Actually planned to have one dashboard kind of setup to show top 5 products
        path: 'home',
        canActivate: [AuthService],
        component: ProductListComponent
      },
      {
        path: 'dashboard',
        redirectTo: 'home',
        pathMatch: 'full'
      },
      {
        path: 'products',
        canActivate: [AuthService],
        loadChildren: () => import('../product/product.module').then(m => m.ProductModule)
      },
      {
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

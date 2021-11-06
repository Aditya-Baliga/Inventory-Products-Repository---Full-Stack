import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

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
        redirectTo: 'products',
        pathMatch: 'full'
      },
      {
        path: 'home',
        redirectTo: 'products',
        pathMatch: 'full'
      },
      {
        path: 'dashboard',
        redirectTo: 'products',
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

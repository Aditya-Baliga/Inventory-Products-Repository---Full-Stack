import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';

import { MainRoutingModule } from './main-routing.module';
import { MainComponent } from './main.component';
import { AuthService } from './authentication/auth.service';
import { UilibraryModule } from '../shared/uilibrary/uilibrary.module';
import { ProductModule } from '../product/product.module';
import { LoginComponent } from './login/login.component';
import { HeaderComponent } from './header/header.component';



@NgModule({
  declarations: [
    MainComponent,
    LoginComponent,
    HeaderComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
    MainRoutingModule,
    UilibraryModule,
    ProductModule
  ],
  providers: [AuthService]
})
export class MainModule { }

import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';
import { ConfirmationService, MessageService } from 'primeng/api';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainModule } from './main/main.module';
import { EnvServiceProvider } from './shared/environments/environment.factory.service';
import { BaseHttpService } from './shared/base/base-http.service';
import { HttpErrorHandler } from './shared/base/http-error-handler.service';
import { AuthService } from './main/authentication/auth.service';
import { AuthInterceptor } from './shared/auth-interceptor/auth.interceptor';

@NgModule({
  imports: [
    // BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    AppRoutingModule,
    MainModule,
  ],
  declarations: [
    AppComponent
  ],
  providers: [
    BaseHttpService, HttpErrorHandler,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    AuthService, ReactiveFormsModule,
    MessageService, ConfirmationService,
    EnvServiceProvider],
  bootstrap: [AppComponent]
})
export class AppModule {

}

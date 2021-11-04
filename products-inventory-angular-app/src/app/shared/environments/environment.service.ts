import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EnvironmentService {
  environment: string = '';
  PRODUCT_SERVER_URL: string = '';
  IDP_URL: string = '';
  auth: any = {};


  constructor() {
    this.auth['ClientId'] = '';
    this.auth['UserPoolId'] = '';
   }
}

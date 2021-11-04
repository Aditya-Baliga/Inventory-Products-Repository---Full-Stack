import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

import { BaseHttpService } from 'src/app/shared/base/base-http.service';
import { HandleError, HttpErrorHandler } from 'src/app/shared/base/http-error-handler.service';
import { EnvironmentService } from 'src/app/shared/environments/environment.service';
import { Product, ProductRequest } from './product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService extends BaseHttpService {
  onListUpdateSubject = new Subject<boolean>();
  private readonly productsUrl: string;
  private readonly handleError: HandleError;

  constructor(http: HttpClient,
    httpErrorHandler: HttpErrorHandler,
    protected readonly envService: EnvironmentService,
  ) {
    super(http);
    this.handleError = httpErrorHandler.createHandleError('Product service');
    this.productsUrl = `${envService.PRODUCT_SERVER_URL}/products/`
  }

  getProducts(params?: any): Observable<Product[]> {
    let urlParam = new HttpParams();
    if (params) {
      const count = params.count;
      if (count) {
        urlParam = urlParam.set('count', count);
      }
    }
    return this.readAll(this.productsUrl, 'Get all products failed', this.handleError, urlParam);
  }

  getProductsByCategoryName(categoryName: string, params?: any): Observable<Product[]> {
    let urlParam = new HttpParams();
    if (params) {
      const count = params.count;
      if (count) {
        urlParam = urlParam.set('count', count);
      }
    }
    const url = `${this.productsUrl}/category/${categoryName}`;
    return this.readAll(url, 'Get all products by category name failed', this.handleError, urlParam);
  }

  getProduct(id: string): Observable<Product> {
    return this.read(this.productsUrl, 'Get product failed', this.handleError, id);
  }

  addProduct(product: ProductRequest) : Observable<Product> {
    return this.create(this.productsUrl, 'Add a new product failed', this.handleError, product);
  }

  deleteProduct(id: string): Observable<{}> {
    return this.delete(this.productsUrl, 'Delete a product failed', this.handleError, id);
  } 

  updateProduct(id: string, product: ProductRequest): Observable<Product> {
    // return this.patch(this.productsUrl, 'Update a product failed', this.handleError, id, product);
    return this.update(this.productsUrl, 'Update a product failed', this.handleError, product, id);
  }

  updateProductListView(): void {
    this.onListUpdateSubject.next(true);
  }

  onUpdateProductList(): Observable<boolean> {
    return this.onListUpdateSubject.asObservable();
  }

  transformToProductRequest(productFormData: any): ProductRequest {
    const productCreateRequest = new ProductRequest();
    productCreateRequest.category = productFormData.category;
    productCreateRequest.description = productFormData.description;
    productCreateRequest.name = productFormData.name;
    productCreateRequest.units= parseInt(productFormData.units);
    return productCreateRequest;
  }

}

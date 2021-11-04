import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';


import { HandleError } from './http-error-handler.service';

@Injectable({
  providedIn: 'root'
})
export class BaseHttpService {
  protected headers: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json',
  });
  protected httpOptions = {
    headers: this.headers
  }

  constructor(protected http: HttpClient) { }

  readAll(url: string, serviceMethodErrorMessage: string, handleError: HandleError, urlParams?: HttpParams): Observable<any[]> {
    return this.http.get<Object[]>(url, { params: urlParams })
      .pipe(
        tap(data => this.prepareAllAfterRead(data)),
        catchError(handleError(serviceMethodErrorMessage, []))
      );
  }

  read(url: string, serviceMethodErrorMessage: string, handleError: HandleError, id: string, responseHeader?: any): Observable<any> {
    const readUrl = id ? `${url}${id}/` : `${url}`;
    return this.http.get<any>(readUrl, responseHeader)
      .pipe(
        tap(data => this.prepareAfterRead(data)),
        catchError(handleError(serviceMethodErrorMessage, {}))
      );
  }

  create(url: string, serviceMethodErrorMessage: string, handleError: HandleError, body: any): Observable<any> {
    return this.http.post<any>(url, body, this.httpOptions)
      .pipe(
        catchError(handleError(serviceMethodErrorMessage, body))
      );
  }

  update(url: string, serviceMethodErrorMessage: string, handleError: HandleError, body: any, id:string): Observable<any> {
    const putUrl = id ? `${url}${id}/` : `${url}`;
    return this.http.put<any>(putUrl, body, this.httpOptions)
      .pipe(
        catchError(handleError(serviceMethodErrorMessage, body))
      );
  }

  patch(url: string, serviceMethodErrorMessage: string, handleError: HandleError, id: string, patchDetails: any): Observable<any> {
    if (id) {
      url = `${url}${id}/`;
    }

    return this.http.patch<any>(url, patchDetails, this.httpOptions)
      .pipe(
        tap(data => this.prepareAfterUpdate(data)),
        catchError(handleError(serviceMethodErrorMessage, patchDetails))
      );
  }

  delete(url: string, serviceMethodErrorMessage: string, handleError: HandleError, id: string): Observable<any> {
    const urlWithId = `${url}${id}/`;
    return this.http.delete<any>(urlWithId, this.httpOptions)
      .pipe(
        catchError(handleError(serviceMethodErrorMessage))
      );
  }

  protected prepareAfterRead(item: any): any {
    return item;
  }

  protected prepareAllAfterRead(items: Object[]): any {
    return items;
  }

  protected prepareBeforeUpdate(item: any): any {
    return item;
  }

  protected prepareAfterUpdate(item: any): any {
    return item;
  }

  protected prepareAllBeforeUpdate(items: any[]): any {
    return items;
  }

  protected prepareBeforeCreate(item: any): any {
    return item;
  }

  protected prepareAllBeforeCreate(items: any[]): any {
    return items;
  }

}
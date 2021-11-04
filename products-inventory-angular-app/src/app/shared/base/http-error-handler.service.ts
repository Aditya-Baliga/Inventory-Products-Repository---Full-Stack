import { Injectable } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http'
import { Observable, throwError } from 'rxjs';
import { MessageService } from 'primeng/api';


export type HandleError = <T> (operation?: string, result?: T) => (error: HttpErrorResponse) => Observable<T>;

@Injectable()
export class HttpErrorHandler {

  constructor(
    private readonly messageService: MessageService,
  ) { }

  createHandleError = (serviceName = '') => <T>
    (operationErrorMessage = 'operationErrorMessage'): any => this.handleError(serviceName, operationErrorMessage)

  handleError<T>(serviceName = '', operationErrorMessage = 'operationErrorMessage') {
    return (errorResponse: HttpErrorResponse): Observable<T> => {
      const errorMessage = (errorResponse.error.message) ? errorResponse.error.message : '';
      const errorCode: string = errorResponse.error.errorCode;
      const rootCause: string = (errorResponse.error.rootCause) ? errorResponse.error.rootCause : '';

      this.messageService.add({
        severity: 'error', summary: `Error in Server`, detail: (errorCode == '500') ? `${serviceName} ${operationErrorMessage} ` :
          (errorMessage) ? `${errorMessage} ${rootCause}` : `${serviceName} ${operationErrorMessage} `
      });
      console.error('Error', errorResponse);
      return throwError(errorMessage);
    }
  }

}

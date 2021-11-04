import { HttpEvent, HttpRequest, HttpHandler, HttpInterceptor } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { AuthService } from "src/app/main/authentication/auth.service";
import { ProductService } from "src/app/product/product-shared/product.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor(private authService: AuthService) { 
   
    }
     

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const authToken = this.authService.getAuthToken();

        if(authToken) {
            const secureRequest = req.clone({
                setHeaders: {
                    'Authorization': authToken
                }
            });
            return next.handle(secureRequest);
        }


        // const secureRequest = request.clone({ headers: request.headers.set('Authorization', this.authService.authToken) });

        console.log('in intercept');
        return next.handle(req)
    }

}
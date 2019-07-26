import { HttpInterceptor, HttpRequest, HttpHandler, HttpEventType } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';

export class AuthInterceptor implements HttpInterceptor {
         constructor(private router: Router) {}

         intercept(request: HttpRequest<any>, next: HttpHandler) {
           return next.handle(request).pipe(
             tap(
               event => {
                    console.log(event);
                    
                 if (event.type === HttpEventType.Response) {
                   console.log(event.body);
                 }
               },
               error => {
                 console.log("Error");

                 if (error.status === 401) {
                  this.router.navigateByUrl("/");
                 }

                 if (error.status === 404) {
                   this.router.navigateByUrl("/404");
                 }

                 if (error.status === 500) {
                   this.router.navigateByUrl("/500");
                 }
               }
             )
           );
         }
       }

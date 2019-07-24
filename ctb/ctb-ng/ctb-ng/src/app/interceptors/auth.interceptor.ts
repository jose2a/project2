import { HttpInterceptor, HttpRequest, HttpHandler, HttpEventType } from '@angular/common/http';
import { tap } from 'rxjs/operators';

export class AuthInterceptor implements HttpInterceptor {
  constructor() {}

  intercept(request: HttpRequest<any>, next: HttpHandler) {
       return next.handle(request).pipe(
            tap(event => {
                 if (event.type === HttpEventType.Response) {
                      console.log(event.body);
                 }

            },
            error => {
                 console.log('Error');

                 if (error.status === 401) {
                     console.log('Not authenticated');
               }

            }
            )
       );
  }
}

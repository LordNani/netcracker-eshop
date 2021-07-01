import {Injectable, Injector} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map, tap} from 'rxjs/operators';
import {AuthService} from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor {

  constructor(private injector: Injector, private authService: AuthService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // add auth header with jwt on request and take jwt on response
    const tokenRequest = (document.cookie.split(';').find(x => x.includes('Token')) || '=').split('=')[1];
    // const tokenRequest = localStorage.getItem('token');
    // console.log('get header from local storage: ' + tokenRequest);
    request = request.clone({
      setHeaders: {Authorization: `${tokenRequest}`}
    });
    return next.handle(request).pipe(
      tap(
        event => {
          if (event instanceof HttpResponse) {
            const tokenResponse = event.headers.get('Authorization');
            if (tokenResponse !== null){
              // localStorage.setItem('token', tokenResponse);
              const expires = new Date(Date.now() + 7 * 24 * 60 * 60 * 1000).toUTCString();
              document.cookie = `Token=${tokenResponse}; expires=${expires}; path=/`;
              // console.log('set token in local storage' + tokenResponse);
            }
          }
        }
      )
    );
  }
}

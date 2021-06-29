import {AuthService} from '../_service/auth.service';
import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {AlertService} from '../_service/alert.service';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService,
              private  alertService: AlertService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(catchError(err => {
      if ([401, 403].includes(err.status)) {
        if (err.url.includes('login')){
          this.alertService.error(`
                        <h4>Wrong password or email</h4>
                    `, { autoClose: false, keepAfterRouteChange: true });
        }
        console.log('now is log out this app');
        this.authService.logout();
        }



      const error = (err && err.error && err.error.message) || err.statusText;
      console.error(err);
      return throwError(error);
    }));
  }
}

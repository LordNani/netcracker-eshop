import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {BehaviorSubject, Observable} from 'rxjs';
import {User} from '../../_model/user';
import {Role} from '../../_model/role';
import {Status} from '../../_model/status';
import {CookieService} from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = 'http://localhost:8081/user';

  role: Role;
  status: Status;
  userId: number;

  constructor(private http: HttpClient,
              private router: Router,
              private cookie: CookieService) {
  }

  registerUser(user: User): Observable<any> {
    const url = `${this.baseUrl}/register`;
    return this.http.post(url, user);
  }

  getToken(user: User): Observable<any> {
    const url = `${this.baseUrl}/token`;
    return this.http.post(url, user);
  }

  validateResetToken(token: string): Observable<any> {
    const url = `${this.baseUrl}/validate-reset-token`;
    return this.http.post(url, {token});
  }

  loginUser(user: User): Observable<any> {
    const url = `${this.baseUrl}/login`;
    return this.http.post(url, user);
  }

  getUserRole(login: string): void {
    const url = `${this.baseUrl}/role`;
    this.http.get(url + '?login=' + login, {observe: 'response'}).subscribe(
      res => {
        const body = JSON.parse(JSON.stringify(res)).body;
        if (body != null) {
          this.role = body.userRole;
          this.status = body.userStatus;
          localStorage.setItem('globalRole', this.role);
          localStorage.setItem('globalStatus', this.status);
        }
      });
  }

  logout(): void {
    this.cookie.deleteAll('/');
    localStorage.removeItem('globalRole');
    localStorage.removeItem('globalStatus');
    localStorage.removeItem('login');
    localStorage.removeItem('idUser');
    this.role = Role.USER;
    this.status = Status.INACTIVE;
    this.router.navigate(['/login']);
  }

  verifyEmail(token: string): Observable<any> {
    const url = `${this.baseUrl}/confirm-account`;
    return this.http.get(url + '?token=' + token);
  }

  setUser(): any {
    this.role = Role.USER;
    this.status = Status.INACTIVE;

    this.getUserRole(localStorage.getItem('login'));
    const role = localStorage.getItem('globalRole');
    const status = localStorage.getItem('globalStatus');

    if (role !== null && status !== null) {
      this.role = Role[role];
      this.status = Status[status];
    }

    return this.http.get<any>(this.baseUrl);
  }

  resetPassword(token: string, password: string, confirmPassword: string): Observable<any> {
    const url = `${this.baseUrl}/reset-password`;
    return this.http.post(url, {token, password, confirmPassword});
  }

  forgotPassword(email: string): Observable<any> {
    const url = `${this.baseUrl}/forgot-password`;
    return this.http.post(url, {email});
  }

}

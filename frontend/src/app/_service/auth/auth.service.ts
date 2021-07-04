import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {BehaviorSubject, Observable, Subject} from 'rxjs';
import {User} from '../../_model/user';
import {map} from 'rxjs/operators';
import {Role} from '../../_model/role';
import {Status} from '../../_model/status';
import {CookieService} from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private userEmailUrl = 'http://localhost:8081/user/email';
  private registerUrl = 'http://localhost:8081/user/register';
  private loginUrl = 'http://localhost:8081/user/login';
  private roleUrl = 'http://localhost:8081/user/role';
  private baseUrl = 'http://localhost:8081';

  role: Role;
  status: Status;
  userId: number;

  private accountSubject: BehaviorSubject<User>;
  public account: Observable<User>;
  private refreshTokenTimeout;

  constructor(private http: HttpClient,
              private router: Router,
              private cookie: CookieService) {
    this.accountSubject = new BehaviorSubject<User>(null);
    this.account = this.accountSubject.asObservable();

  }

  public get accountValue(): User {
    return this.accountSubject.value;
  }

  // sendUserEmail(email: string): Observable<any>{
  //   return this.http.post(this.userEmailUrl, email);
  // }
  registerUser(user: User): Observable<any> {
    return this.http.post(this.registerUrl, user);
  }

  loginUser(user: User): Observable<any> {
    return this.http.post(this.loginUrl, user);
  }

  getUserRole(login: string): void{
    this.http.get(this.roleUrl + '?login=' + login, {observe: 'response'}).subscribe((res) => {
      const body = JSON.parse(JSON.stringify(res)).body;
      if (body != null) {
        this.role = body.userRole;
        this.status = body.userStatus;
        localStorage.setItem('globalRole', this.role);
        localStorage.setItem('globalStatus', this.status);
      }
    });
  }

  getToken(user: User): Observable<any> {
    return this.http.post('http://localhost:8081/user/token', user);
  }

  logout(): void {
    this.cookie.deleteAll('/');
    localStorage.removeItem('globalRole');
    localStorage.removeItem('globalStatus');
    localStorage.removeItem('login');
    localStorage.removeItem('idUser');
    this.role = Role.USER;
    this.status = Status.ANONYMOUS;
    this.stopRefreshTokenTimer();
    this.router.navigate(['/login']);
  }

  verifyEmail(token: string): Observable<any> {
    return this.http.get('http://localhost:8081/user/confirm-account?token=' + token);
  }

  setUser(): any {
    this.role = Role.USER;
    this.status = Status.ANONYMOUS;
    this.getUserRole(localStorage.getItem('login'));
    const role = localStorage.getItem('globalRole');
    const status = localStorage.getItem('globalStatus');
    if (role !== null && status !== null) {
      this.role = Role[role];
      this.status = Status[status];
    }
    return this.http.get<any>(this.roleUrl + '?login=' + localStorage.getItem('login'));
  }

  validateResetToken(token: string): Observable<any> {
    return this.http.post('http://localhost:8081/user/validate-reset-token', { token });
  }

  resetPassword(token: string, password: string, confirmPassword: string): Observable<any> {
    return this.http.post('http://localhost:8081/user/reset-password', {token, password, confirmPassword});
  }

  forgotPassword(email: string): Observable<any> {
    return this.http.post('http://localhost:8081/user/forgot-password', {email});
  }


  refreshToken(): Observable<any> {
    console.log('refresh-token now');
    return this.http.post<any>(`http://localhost:8081/user/refresh-token`, {}, { withCredentials: true })
      .pipe(map((account) => {
        this.startRefreshTokenTimer();
        return account;
      }));
  }

  startRefreshTokenTimer(): void {
    // const timeout = Date.now() + (59 * 60 * 1000);
    const timeout = (5 * 1000);
    this.refreshTokenTimeout = setTimeout(() => this.refreshToken().subscribe(), timeout);
  }

  stopRefreshTokenTimer(): void {
    clearTimeout(this.refreshTokenTimeout);
  }

}

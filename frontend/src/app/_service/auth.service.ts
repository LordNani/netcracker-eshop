import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {BehaviorSubject, Observable} from 'rxjs';
import {User} from '../_model/user';
import {map} from 'rxjs/operators';
import {Role} from '../_model/role';
import {Status} from '../_model/status';
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
  public globalRole = 'USER';
  public globalStatus = 'ANONYMOUS';

  public user = new User();

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

      this.role = body.userRole;
      this.status = body.userStatus;

      localStorage.setItem('globalRole', this.role);
      localStorage.setItem('globalStatus', this.status);
    });
  }

  getToken(): string {
    return localStorage.getItem('token');
  }

  logout(): void {
    this.cookie.deleteAll('/');

    // localStorage.removeItem('token');
    localStorage.removeItem('globalRole');
    localStorage.removeItem('globalStatus');
    localStorage.removeItem('login');
    this.role = Role.USER;
    this.status = Status.ANONYMOUS;
    console.log(this.role);
    console.log(this.status);
    this.router.navigate(['/login']);
  }

  verifyEmail(token: string): Observable<any> {
    return this.http.post('http://localhost:8081/user/confirm-account', {token});
  }

  setUser(): any {
    this.role = Role.USER;
    this.status = Status.ANONYMOUS;
    const role = localStorage.getItem('globalRole');
    const status = localStorage.getItem('globalStatus');
    if (role !== null && status !== null) {
      this.role = Role[role];
      this.status = Status[status];
    }
    return this.http.get<any>(this.roleUrl + '?login=' + localStorage.getItem('login'), {withCredentials: true});
  }

}

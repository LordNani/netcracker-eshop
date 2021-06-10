import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {User} from '../_model/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private userEmailUrl = 'http://localhost:8081/user/email';
  private registerUrl = 'http://localhost:8081/user/register';
  private loginUrl = 'http://localhost:8081/user/login';
  private roleUrl = 'http://localhost:8081/user/role';
  constructor(private http: HttpClient,
              private router: Router) {
  }
  sendUserEmail(email: string): Observable<any>{
    return this.http.post(this.userEmailUrl, email);
  }
  registerUser(user: User): Observable<any>{
    return this.http.post(this.registerUrl, JSON.stringify(user));
  }
  loginUser(user: User): Observable<any>{
    return this.http.post(this.loginUrl, JSON.stringify(user));
  }

  getUserRole(): Observable<any>{
    return this.http.get(this.roleUrl);
  }

  getToken(): string{
    return localStorage.getItem('token');
  }
  logout(): void{
    localStorage.removeItem('token');
    this.router.navigate(['/main']);
  }
  loggedIn(): boolean{
    return !!localStorage.getItem('token');
  }
}

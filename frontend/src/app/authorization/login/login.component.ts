import { Component, OnInit } from '@angular/core';
import {User} from '../../_model/user';
import {Router} from '@angular/router';
import {AuthService} from '../../_service/auth.service';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginUserData = new User();
  error = false;
  constructor(private router: Router,
              private authService: AuthService) { }
  ngOnInit(): void {
  }

  login(): void {
    console.log(this.loginUserData);
    this.authService.loginUser(this.loginUserData).subscribe(
      res => {
        this.error = false;
        localStorage.setItem('token', res.headers.get('Authorization').body);
        this.router.navigate(['/main']);
      },
      error => {
        this.error = true;
        if (error instanceof HttpErrorResponse) {
          if (error.status === 401) {
            console.log(error); // ?????
          }
        }
      }
    );
  }
}

import { Component, OnInit } from '@angular/core';
import {User} from '../../_model/user';
import {Router} from '@angular/router';
import {AuthService} from '../../_services/auth.service';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginUserData: User;
  constructor(private router: Router,
              private authService: AuthService) { }
  ngOnInit(): void {
  }

  login(): void {
    console.log(this.loginUserData);
    this.authService.loginUser(this.loginUserData).subscribe(
      res => {
        localStorage.setItem('token', res.token);
        this.router.navigate(['/main']);
      },
      error => {
        if (error instanceof HttpErrorResponse) {
          if (error.status === 401) {
            console.log(error); // ?????
          }
        }
      }
    );
  }
}

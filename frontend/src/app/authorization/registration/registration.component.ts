import { Component, OnInit } from '@angular/core';
import {User} from '../../_model/user';
import {AuthService} from '../../_service/auth.service';
import {HttpErrorResponse} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  registerUserData = new User();
  error = false;
  constructor(private router: Router,
              private authService: AuthService) {
  }
  ngOnInit(): void {
  }
  register(): void{
    console.log(this.registerUserData);
    this.authService.registerUser(this.registerUserData).subscribe(
      res => {
        this.error = false;
        console.log(res);
        // localStorage.setItem('token', res.token);
      },
      error => {
        if (error instanceof HttpErrorResponse) {
          this.error = true;
          if (error.status === 400) {
            console.log(error); // ?????
            alert('Bad request');
          }
          if (error.status === 401) {
            console.log(error); // ?????
            alert('user with this email already exist');
          }
        }
      }
    );
    if (this.error === false) {
      this.router.navigate(['/main']);
    }
  }
}

import { Component, OnInit } from '@angular/core';
import {User} from '../../_model/user';
import {AuthService} from '../../_services/auth.service';
import {HttpErrorResponse} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  registerUserData = new User();
  constructor(private router: Router,
              private authService: AuthService) {
  }
  ngOnInit(): void {
  }
  register(): void{
    console.log(this.registerUserData);
    this.authService.registerUser(this.registerUserData).subscribe(
      res => {
        localStorage.setItem('token', res.token);
      },
      error => {
        if (error instanceof HttpErrorResponse) {
          if (error.status === 401) {
            console.log(error); // ?????
          }
        }
      }
    );
    this.router.navigate(['/main']);
  }
}

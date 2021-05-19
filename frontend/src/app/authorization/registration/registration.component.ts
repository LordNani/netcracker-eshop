import { Component, OnInit } from '@angular/core';
import {User} from '../../_model/user';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../../_services/auth.service';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  registerUserData: User;
  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private authService: AuthService) { }
  ngOnInit(): void {
    this.activatedRoute.params.subscribe(
      params => this.registerUserData.email = params.email
    );
  }
  register(): void{
    console.log(this.registerUserData);
    this.authService.registerUser(this.registerUserData).subscribe(
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

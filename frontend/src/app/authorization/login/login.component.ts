import { Component, OnInit } from '@angular/core';
import {User} from '../../_model/user';
import {Router} from '@angular/router';
import {AuthService} from '../../_service/auth.service';
import {HttpErrorResponse} from '@angular/common/http';
import {AlertService} from '../../_service/alert.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginUserData = new User();
  error = false;
  aFormGroup: FormGroup;
  siteKey = '6Le0Rh8bAAAAACqRjMrcm3VZKFVZzYxp8QBZ3UC_';

  constructor(private router: Router,
              private authService: AuthService,
              private alertService: AlertService,
              private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.aFormGroup = this.formBuilder.group({
      recaptcha: ['', Validators.required]
    });
  }

  getRole(): void {
    this.authService.getUserRole(this.loginUserData.userLogin);
  }

  login(): void {
    this.getRole();

    this.authService.loginUser(this.loginUserData).subscribe(
      res => {
        this.error = false;
        localStorage.setItem('login', this.loginUserData.userLogin);
        this.router.navigate(['/main']);
      },
      error => {
        this.error = true;
        if (error instanceof HttpErrorResponse) {
          if (error.status === 403) {
            console.log(error); // ?????
          }
        }
      }
    );

  }
}

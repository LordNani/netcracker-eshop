import {Component, OnInit} from '@angular/core';
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
  siteKey = '6Lf8SSgbAAAAALxW_hIMBPJeKQzgvvg7NmbCzVO2';
  loginForm: FormGroup;
  submitted = false;
  invalidLogin = false;
  captchaError = false;
  loginResponse: string;
  failedRegistration = 0;

  constructor(private router: Router,
              private authService: AuthService,
              private alertService: AlertService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required],
      recaptcha: ['', Validators.required]
    });
  }

  onSubmit(): void {
    this.submitted = true;
    if (this.loginForm.invalid) {
      return;
    }
    const response = grecaptcha.getResponse();
    if (response.length === 0) {
      this.captchaError = true;
      return;
    }
    const login = new User();
    login.userLogin = this.loginForm.controls.email.value;
    login.userPassword = this.loginForm.controls.password.value;

    login.recaptchaResponse = response;
    console.log(response);
    // login.recaptchaResponse = undefined;

    this.login(login);

    // this.authService.loginUser(login).subscribe(data => {
    //   if (data.status === 200) {
    //     this.router.navigate(['/main']);
    //   } else {
    //     this.invalidLogin = true;
    //     this.loginResponse = data.message;
    //   }
    //   grecaptcha.reset();
    // });
  }

  getRole(): void {
    this.authService.getUserRole(this.loginUserData.userLogin);
  }

  login(loginData: User): void {
    this.authService.loginUser(loginData).subscribe(
      res => {
        this.error = false;
        this.authService.role = res.userRole;
        this.authService.status = res.userStatus;
        this.loginUserData = res;
        localStorage.setItem('login', this.loginUserData.userLogin);
        this.router.navigate(['/main']);
      },
      error => {
        this.error = true;
        console.log(error);
        this.failedRegistration++;
        if (this.failedRegistration >= 5) {
          console.log('show recaptcha now!!!!!!');
        }
        this.alertService.error(error);
        this.invalidLogin = true;
        this.loginResponse = error.message;
        grecaptcha.reset();
      }
    );
    if (!this.error) {
      loginData.recaptchaResponse = undefined;
      this.authService.getToken(loginData).subscribe(
        res => {
        });
    }
    // this.getRole();
  }

  resolved(captchaResponse: string): void {
    console.log(`Resolved response token: ${captchaResponse}`);
  }

  handleSuccess(captchaResponse: string): void {
    console.log(`Resolved response token: ${captchaResponse}`);
  }
}

import {Component, OnInit} from '@angular/core';
import {User} from '../../_model/user';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../../_service/auth/auth.service';
import {HttpErrorResponse} from '@angular/common/http';
import {AlertService} from '../../_service/alert/alert.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ValidationMessages} from '../../_model/labels/validation.messages';
import {ErrorMessages} from "../../_model/labels/error.messages";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  emailErrorMessage = ValidationMessages.email;
  passwordErrorMessage = ValidationMessages.password;

  loginUserData = new User();
  error = false;
  siteKey = '6Lf8SSgbAAAAALxW_hIMBPJeKQzgvvg7NmbCzVO2';
  defaultKey = '6Lf8SSgbAAAAALxW_hIMBPJeKQzgvvg7123CzVO2';
  loginForm: FormGroup;
  submitted = false;
  invalidLogin = false;
  captchaError = false;
  loginResponse: string;
  failedRegistration = 0;

  constructor(private router: Router,
              private authService: AuthService,
              private alertService: AlertService,
              private formBuilder: FormBuilder,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required,
                      Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,20}$')]],
      recaptcha: ['']
    });
  }

  onSubmit(): void {
    this.submitted = true;
    if (this.loginForm.invalid) {
      return;
    }

    if (this.failedRegistration >= 5) {
      const response = grecaptcha?.getResponse();
      if (response.length === 0) {
        this.captchaError = true;
        return;
      }
    }

    const login = new User();
    login.userLogin = this.loginForm.controls.email.value;
    login.userPassword = this.loginForm.controls.password.value;

    login.recaptchaResponse = this.loginForm.controls.recaptcha.value;
    console.log(login.recaptchaResponse);
    // login.recaptchaResponse = undefined;

    this.login(login);
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
        this.authService.userId = res.id;
        localStorage.setItem('login', this.loginUserData.userLogin);
        localStorage.setItem('idUser', res.id);
        if (!this.error) {
          loginData.recaptchaResponse = undefined;
          this.authService.getToken(loginData).subscribe(
            response => {
              this.authService.startRefreshTokenTimer();
            });
        }
        const returnUrl = this.route.snapshot.queryParams.returnUrl || '/';
        this.router.navigateByUrl(returnUrl);
        // this.router.navigate(['/main']);
      },
      error => {
        this.error = true;
        console.log(error);
        this.failedRegistration++;
        if (this.failedRegistration >= 5) {
          console.log('show recaptcha now!!!!!!');
        }
        this.alertService.error(ErrorMessages[error.error.message], { autoClose: false });
        this.invalidLogin = true;
        this.loginResponse = error.message;
        grecaptcha.reset();
      }
    );
  }
}

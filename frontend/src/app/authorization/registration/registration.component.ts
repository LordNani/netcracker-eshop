import {Component, OnDestroy, OnInit} from '@angular/core';
import {User} from '../../_model/user';
import {AuthService} from '../../_service/auth/auth.service';
import {Router} from '@angular/router';
import {AlertService} from '../../_service/alert/alert.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ValidationMessages} from '../../_model/labels/validation.messages';
import {ErrorMessages} from '../../_model/labels/error.messages';
import {AutoUnsubscribe} from 'ngx-auto-unsubscribe';

@AutoUnsubscribe()
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit, OnDestroy {
  registerUserData = new User();
  registerForm: FormGroup;
  submitted = false;

  firstNameErrorMessage = ValidationMessages.firstName;
  lastNameErrorMessage = ValidationMessages.lastName;
  emailErrorMessage = ValidationMessages.email;
  passwordErrorMessage = ValidationMessages.password;

  constructor(private router: Router,
              private authService: AuthService,
              private alertService: AlertService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      firstName: ['', [Validators.required, Validators.min(1), Validators.maxLength(35)]],
      lastName: ['', [Validators.required, Validators.min(1), Validators.maxLength(35)]],
      email: ['', [Validators.required,  Validators.email]],
      password: ['', [Validators.required,
        Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,20}$')]]
    });
  }

  ngOnDestroy(): void {
  }

  register(): void {
    this.authService.registerUser(this.registerUserData).subscribe(
      res => {
        this.alertService.success('Registration successful, confirm email and login', { keepAfterRouteChange: true });
        this.router.navigate(['/login']);
      },
      error => {
        this.alertService.error(ErrorMessages[error.error.message], { autoClose: false });
      }
    );
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.registerForm.invalid) {
      return;
    }

    this.registerUserData.userName = this.registerForm.controls.firstName.value;
    this.registerUserData.userSurname = this.registerForm.controls.lastName.value;
    this.registerUserData.userLogin = this.registerForm.controls.email.value;
    this.registerUserData.userPassword = this.registerForm.controls.password.value;

    this.register();
  }
}

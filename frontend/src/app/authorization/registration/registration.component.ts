import {Component, OnInit} from '@angular/core';
import {User} from '../../_model/user';
import {AuthService} from '../../_service/auth.service';
import {HttpErrorResponse} from '@angular/common/http';
import {Router} from '@angular/router';
import {AlertService} from '../../_service/alert.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  registerUserData = new User();
  registerForm: FormGroup;
  submitted = false;

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
        Validators.pattern('(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[A-Za-z\d].{8,20}')]]
    });
  }

  register(): void {
    console.log(this.registerUserData);
    this.authService.registerUser(this.registerUserData).subscribe(
      res => {
        console.log(res);
        this.alertService.success('Registration successful, confirm email and login', { keepAfterRouteChange: true });
        this.router.navigate(['/login']);
      },
      error => {
        console.log(error);
        this.alertService.error(error, { autoClose: false });
      }
    );
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.registerForm.invalid) {
      return;
    }

    this.registerUserData.firstName = this.registerForm.controls.firstName.value;
    this.registerUserData.lastName = this.registerForm.controls.lastName.value;
    this.registerUserData.userLogin = this.registerForm.controls.email.value;
    this.registerUserData.userPassword = this.registerForm.controls.password.value;

    this.register();
  }
}

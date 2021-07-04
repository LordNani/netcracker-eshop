import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../_service/auth/auth.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AlertService} from '../../_service/alert/alert.service';
import {finalize, first} from 'rxjs/operators';
import {ValidationMessages} from '../../_model/labels/validation.messages';
import {ErrorMessages} from "../../_model/labels/error.messages";

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  emailErrorMessage = ValidationMessages.email;

  form: FormGroup;
  loading = false;
  submitted = false;

  constructor(private formBuilder: FormBuilder,
              private authService: AuthService,
              private alertService: AlertService) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]]
    });
  }

  onSubmit(): void {
    this.submitted = true;

    this.alertService.clear();

    if (this.form.invalid) {
      return;
    }

    this.loading = true;
    this.alertService.clear();
    this.authService.forgotPassword(this.form.controls.email.value)
      .pipe(first())
      .pipe(finalize(() => this.loading = false))
      .subscribe({
        next: () => this.alertService.success('Please check your email for password reset instructions', { autoClose: false }),
        error: error => this.alertService.error(ErrorMessages[error.error.message], { autoClose: false })
      });
  }

}

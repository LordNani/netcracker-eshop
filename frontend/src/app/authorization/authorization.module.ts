import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AppRoutingModule } from '../app-routing.module';
import {AuthService} from '../_service/auth.service';
import { VerifyEmailComponent } from './mail/verify-email.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import {NgxCaptchaModule} from 'ngx-captcha';


@NgModule({
  declarations: [
    LoginComponent,
    RegistrationComponent,
    VerifyEmailComponent,
    ResetPasswordComponent,
    ForgotPasswordComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    NgxCaptchaModule,
  ],
  providers: [
    AuthService
  ]
})
export class AuthorizationModule { }

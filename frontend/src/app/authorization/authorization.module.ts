import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AppRoutingModule } from '../app-routing.module';
import {AuthService} from '../_service/auth.service';
import { VerifyEmailComponent } from './mail/verify-email/verify-email.component';
import {RecaptchaModule} from "ng-recaptcha";
import {NgxCaptchaModule} from "ngx-captcha";



@NgModule({
  declarations: [
    LoginComponent,
    RegistrationComponent,
    VerifyEmailComponent
  ],
    imports: [
        CommonModule,
        FormsModule,
        AppRoutingModule,
        ReactiveFormsModule,
        RecaptchaModule,
        NgxCaptchaModule
    ],
  providers: [
    AuthService
  ]
})
export class AuthorizationModule { }

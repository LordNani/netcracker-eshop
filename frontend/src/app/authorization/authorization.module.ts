import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import {FormsModule} from '@angular/forms';
import { EmailComponent } from './email/email.component';
import {AuthService} from '../_services/auth.service';



@NgModule({
  declarations: [
    LoginComponent,
    RegistrationComponent,
    EmailComponent
  ],
  imports: [
    CommonModule,
    FormsModule
  ],
  providers: [
    AuthService
  ]
})
export class AuthorizationModule { }

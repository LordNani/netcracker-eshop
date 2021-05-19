import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { MainPageComponent } from './main-page/main-page.component';
import {AuthorizationModule} from './authorization/authorization.module';
import {AuthService} from './_services/auth.service';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import {TokenInterceptorService} from './_services/token-interceptor.service';
@NgModule({
  declarations: [
    AppComponent,
    MainPageComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AuthorizationModule,
    HttpClientModule
  ],
  providers: [AuthService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }

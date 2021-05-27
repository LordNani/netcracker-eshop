import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { MainPageComponent } from './main-page/main-page.component';
import { AuthorizationModule } from './authorization/authorization.module';
import { AuctionsModule } from './auctions/auctions.module';
import { ProductsModule } from './products/products.module';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { SettingsComponent } from './settings/settings.component';
import {AuthService} from './_service/auth.service';
import {TokenInterceptorService} from './_service/token-interceptor.service';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import {CommonModule} from './common/common.module';
import {NavBarModule} from './nav-bar/nav-bar.module';

@NgModule({
  declarations: [
    AppComponent,
    MainPageComponent,
    SettingsComponent,
    ShoppingCartComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AuthorizationModule,
    AuctionsModule,
    ProductsModule,
    HttpClientModule,
    CommonModule,
    NavBarModule
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

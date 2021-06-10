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
// import {SettingsModule} from './settings/settings.module';
// import {UserService} from './_service/user.service';
import {WorkSpaceModule} from './work-space/work-space.module';
// import { ProfileComponent } from './account/profile/profile.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatIconModule} from '@angular/material/icon';
// import { SearchComponent } from './account/search/search.component';
// import { RestComponent } from './account/rest/rest.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
// import { EditComponent } from './account/edit/edit.component';
import {MatDialogModule, MatDialogConfig} from '@angular/material/dialog';
import {MatRadioModule} from '@angular/material/radio';
// import {MatOptionModule} from "@angular/material/core";

@NgModule({
  declarations: [
    AppComponent,
    MainPageComponent,
    SettingsComponent,
    ShoppingCartComponent,
    // ProfileComponent,
    // SearchComponent,
    // RestComponent,
    // EditComponent,
    // SearchComponent,
    SettingsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AuthorizationModule,
    AuctionsModule,
    ProductsModule,
    HttpClientModule,
    CommonModule,
    NavBarModule,
    // SettingsModule,
    WorkSpaceModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatIconModule,
    NgbModule,
    MatDialogModule,
    MatRadioModule
    // MatOptionModule

  ],
  providers: [AuthService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    }],
  bootstrap: [AppComponent],
  // entryComponents: [ProfileComponent]
})
export class AppModule { }

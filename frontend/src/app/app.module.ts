import {APP_INITIALIZER, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {MainPageComponent} from './main-page/main-page.component';
import {AuthorizationModule} from './authorization/authorization.module';
import {AuctionsModule} from './auctions/auctions.module';
import {ProductsModule} from './products/products.module';
import {ShoppingCartComponent} from './shopping-cart/shopping-cart.component';
import {SettingsComponent} from './settings/settings.component';
import {AuthService} from './_service/auth.service';
import {TokenInterceptorService} from './_service/token-interceptor.service';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import {CommonModule} from './common/common.module';
import {NavBarModule} from './nav-bar/nav-bar.module';
import {ProductCatalogComponent} from './product-catalog/product-catalog.component';
import {TempProductComponent} from './product-catalog/temp-product/temp-product.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {JwPaginationModule} from 'jw-angular-pagination';
import {AlertComponent} from './alert/alert.component';
import {appInitializer} from './_helper/app.initializer';
import {ErrorInterceptor} from './_helper/error.interceptor';
import {CookieService} from 'ngx-cookie-service';
import {ProfileComponent} from './account/profile/profile.component';
import {AdminWorkSpaceLinkComponent} from './nav-bar/components/admin-work-space-link/admin-work-space-link.component';
import {SearchComponent} from './account/search/search.component';
import {MatIconModule} from '@angular/material/icon';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatRadioModule} from '@angular/material/radio';
import {MatOptionModule} from '@angular/material/core';
import {MatInputModule} from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import {MatButtonModule} from '@angular/material/button';
import { EditSettingsComponent } from './settings/edit/edit-settings/edit-settings.component';
import {OrderHistoryComponent} from './settings/order-history/order-history.component';
import {OrderDetailsComponent} from './settings/order-history/order-details/order-details.component';
import {NgbPaginationModule} from '@ng-bootstrap/ng-bootstrap';
import {CoucabComponent} from './account/courier-cabinet/coucab.component';
import {NotificationComponent} from './socket/notifications/notification.component';

@NgModule({
    declarations: [
        AppComponent,
        MainPageComponent,
        AlertComponent,
        SettingsComponent,
        ShoppingCartComponent,
        ProductCatalogComponent,
        ProfileComponent,
        AdminWorkSpaceLinkComponent,
        SearchComponent,
        EditSettingsComponent,
        OrderHistoryComponent,
        CoucabComponent,
        // NotificationComponent,
        OrderDetailsComponent
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
        FormsModule,
        JwPaginationModule,
        ReactiveFormsModule,
        MatIconModule,
        MatFormFieldModule,
        MatRadioModule,
        MatOptionModule,
        MatInputModule,
        MatDatepickerModule,
        MatButtonModule,
        MatFormFieldModule,
        MatNativeDateModule,
        NgbPaginationModule,
    ],
  providers: [AuthService, CookieService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    },
    {
      provide: APP_INITIALIZER,
      useFactory: appInitializer,
      multi: true,
      deps: [AuthService]
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorInterceptor,
      multi: true
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}

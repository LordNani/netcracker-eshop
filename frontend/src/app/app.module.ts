import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { MainPageComponent } from './main-page/main-page.component';
import {AuthorizationModule} from './authorization/authorization.module';
// import { ProductsComponent } from './Components/products/products.component';
// import { AuctionsComponent } from './Components/auctions/auctions.component';
// import { ShoppingCartComponent } from './Components/shopping-cart/shopping-cart.component';
// import { SettingsComponent } from './Components/settings/settings.component';
// import { ProductComponent } from './Components/product/product.component';
// import { AuctionComponent } from './Components/auction/auction.component';
// import { BidComponent } from './Components/bid/bid.component';

@NgModule({
  declarations: [
    AppComponent,
    MainPageComponent,
    // ProductsComponent,
    // AuctionsComponent,
    // ShoppingCartComponent,
    // SettingsComponent,
    // ProductComponent,
    // AuctionComponent,
    // BidComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AuthorizationModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

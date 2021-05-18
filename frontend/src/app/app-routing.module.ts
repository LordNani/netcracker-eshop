import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {MainPageComponent} from './Components/main-page/main-page.component';
import {LoginComponent} from './Components/authorization/login/login.component';
import {RegistrationComponent} from './Components/authorization/registration/registration.component';
// import {AuctionsComponent} from './Components/auctions/auctions.component';
// import {ProductsComponent} from './Components/products/products.component';
// import {ShoppingCartComponent} from './Components/shopping-cart/shopping-cart.component';
// import {SettingsComponent} from './Components/settings/settings.component';
// import {ProductComponent} from './Components/product/product.component';
// import {AuctionComponent} from './Components/auction/auction.component';
// import {BidComponent} from './Components/bid/bid.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'main',
    pathMatch: 'full'
  },
  {
    path: 'main',
    component: MainPageComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegistrationComponent
  }
  // {
  //   path: 'auctions',
  //   component: AuctionsComponent
  // },
  // {
  //   path: 'auction',
  //   component: AuctionComponent
  // },
  // {
  //   path: 'bid',
  //   component: BidComponent
  // },
  // {
  //   path: 'products',
  //   component: ProductsComponent
  // },
  // {
  //   path: 'product',
  //   component: ProductComponent
  // },
  // {
  //   path: 'shopping-cart',
  //   component: ShoppingCartComponent
  // },
  // {
  //   path: 'settings',
  //   component: SettingsComponent
  // }
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}

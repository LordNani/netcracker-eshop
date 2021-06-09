import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {MainPageComponent} from './main-page/main-page.component';
import {LoginComponent} from './authorization/login/login.component';
import {RegistrationComponent} from './authorization/registration/registration.component';
import {ShoppingCartComponent} from './shopping-cart/shopping-cart.component';
import {SettingsComponent} from './settings/settings.component';

import {AuctionComponent} from './auctions/auction/auction.component';
import {AuctionListComponent} from './auctions/auction-list/auction-list.component';
import {BidComponent} from './auctions/bid/bid.component';

import {ProductComponent} from './products/product/product.component';
import {ProductListComponent} from './products/product-list/product-list.component';
import {ProductCatalogComponent} from './product-catalog/product-catalog.component';
import {VerifyEmailComponent} from './authorization/mail/verify-email/verify-email.component';

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
  },
  {
    path: 'auction-list',
    component: AuctionListComponent
  },
  {
    path: 'auction',
    component: AuctionComponent
  },
  {
    path: 'bid',
    component: BidComponent
  },
  {
    path: 'product-list',
    component: ProductListComponent
  },
    {
      path: 'product',
      component: ProductComponent
    },
  {
    path: 'shopping-cart',
    component: ShoppingCartComponent
  },
  {
    path: 'settings',
    component: SettingsComponent
  },
  {
    path: 'working-cabinet',
    component: ProductCatalogComponent
  },
  {
    path: 'verify-email',
    component: VerifyEmailComponent
  }
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

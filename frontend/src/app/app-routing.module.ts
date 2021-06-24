import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {MainPageComponent} from './main-page/main-page.component';
import {LoginComponent} from './authorization/login/login.component';
import {RegistrationComponent} from './authorization/registration/registration.component';
import {ShoppingCartComponent} from './shopping-cart/shopping-cart.component';
import {AuctionComponent} from './auctions/auction/auction.component';
import {AuctionListComponent} from './auctions/auction-list/auction-list.component';
import {BidComponent} from './auctions/bid/bid.component';
import {ProductComponent} from './products/product/product.component';
import {ProductListComponent} from './products/product-list/product-list.component';
// import {SettingsComponent} from './settings/pages/settings/settings.component';
// import {PersonalDataViewComponent} from './settings/components/personal-data-view/personal-data-view.component';
// import {PersonalDataEditComponent} from './settings/components/personal-data-edit/personal-data-edit.component';
import {AdminWorkSpaceLinkComponent} from './nav-bar/components/admin-work-space-link/admin-work-space-link.component';
// import {ProfileComponent} from './account/profile/profile.component';
import {SettingsComponent} from './settings/settings.component';
import {ProductCatalogComponent} from './product-catalog/product-catalog.component';
import {Role} from './_model/role';
import {AuthGuard} from './_helper/auth.guard';
import {VerifyEmailComponent} from './authorization/mail/verify-email/verify-email.component';
import {ForgotPasswordComponent} from './authorization/forgot-password/forgot-password.component';
import {ResetPasswordComponent} from './authorization/reset-password/reset-password.component';
// @ts-ignore
import {SearchComponent} from "./account/search/search.component";


// const settingsChildRoutes: Routes = [
//   { path: 'view', component: PersonalDataViewComponent},
//   { path: 'edit', component: PersonalDataEditComponent},
// ];

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
    path: 'product-list/product/:id',
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
  // {
  //   path: 'settings',
  //   component: SettingsComponent,
  //   children: settingsChildRoutes
  // },
  {
    path: 'search',
    component: SearchComponent

     },
  {
    path: 'working-cabinet',
    component: ProductCatalogComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.MANAGER, Role.ADMIN, Role.COURIER] }
  },
  {
    path: 'verify-email',
    component: VerifyEmailComponent
  },
  {
    path: 'forgot-password',
    component: ForgotPasswordComponent
  },
  {
    path: 'reset-password',
    component: ResetPasswordComponent
  }
  // {
  //   path: 'account/profile',
  //   component: ProfileComponent
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

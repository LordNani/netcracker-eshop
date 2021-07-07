import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {MainPageComponent} from './main-page/main-page.component';
import {LoginComponent} from './authorization/login/login.component';
import {RegistrationComponent} from './authorization/registration/registration.component';
import {ShoppingCartComponent} from './shopping-cart/cart/shopping-cart.component';
import {AuctionComponent} from './auctions/auction/auction.component';
import {AuctionListComponent} from './auctions/auction-list/auction-list.component';
import {BidComponent} from './auctions/bid/bid.component';
import {ProductComponent} from './products/product/product.component';
import {ProductListComponent} from './products/product-list/product-list.component';
// import {SettingsComponent} from './settings/pages/settings/settings.component';
// import {PersonalDataViewComponent} from './settings/components/personal-data-view/personal-data-view.component';
// import {PersonalDataEditComponent} from './settings/components/personal-data-edit/personal-data-edit.component';
// import {ProfileComponent} from './account/profile/profile.component';
// @ts-ignore
import {SearchComponent} from './account/search/search.component';
import {EditSettingsComponent} from './settings/edit-settings/edit-settings.component';
import {AuthGuard} from './_helper/auth.guard';
import {Role} from './_model/role';
import {ResetPasswordComponent} from './authorization/reset-password/reset-password.component';
import {VerifyEmailComponent} from './authorization/mail/verify-email.component';
import {ManagerWorkspaceComponent} from './work-space/manager-workspace/manager-workspace.component';
import {SettingsComponent} from './settings/user-profile/settings.component';
import {ForgotPasswordComponent} from './authorization/forgot-password/forgot-password.component';
import {OrderDetailsComponent} from './settings/order-history/order-details/order-details.component';
import {CoucabComponent} from './account/courier-cabinet/coucab.component';
import {CheckoutComponent} from './checkout/checkout.component';
import {CompareComponent} from './products/compare/compare.component';
import {WorkplaceComponent} from './workplace/workplace.component';
import {NotificationComponent} from "./socket/notifications/notification.component";

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
  {
    path: 'compare',
    component: CompareComponent
  },
  {
    path: 'shopping-cart/checkout',
    component: CheckoutComponent
  },
  {
    path: 'socket',
    component: NotificationComponent
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
    path: 'coucab',
    component: CoucabComponent
  },
  {
    path: 'verify-email',
    component: VerifyEmailComponent
  },
  {
    path: 'workplace',
    component: WorkplaceComponent
  },
  {
    path: 'forgot-password',
    component: ForgotPasswordComponent
  },
  {
    path: 'reset-password',
    component: ResetPasswordComponent

  },
  {
    path: 'manager',
    component: ManagerWorkspaceComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.ADMIN, Role.MANAGER]}
  },
  {
    path: 'verify-email',
    component: VerifyEmailComponent
  },
  {
    path: 'reset-password',
    component: ResetPasswordComponent
  },
  {
    path: 'settings/edit',
    component: EditSettingsComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.USER] }
  },
  {
    path: 'settings/order-details',
    component: OrderDetailsComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.USER]}
  },
  {
    path: 'register',
    component: RegistrationComponent,
    canActivate: [AuthGuard],
    data: {skipException: ['true']}
  },
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [AuthGuard],
    data: {skipException: ['true']}
  },
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

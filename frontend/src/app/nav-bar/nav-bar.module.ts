import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavBarComponent } from './pages/nav-bar/nav-bar.component';
import { HomeLinkComponent } from './components/home-link/home-link.component';
import {HttpClientModule} from '@angular/common/http';
import {RouterModule} from '@angular/router';
import {BaseLinkComponent} from './components/base-link/base-link.component';
import { CatalogueLinkComponent } from './components/catalogue-link/catalogue-link.component';
import { ShoppingCartLinkComponent } from './components/shopping-cart-link/shopping-cart-link.component';
import { AdminWorkSpaceLinkComponent } from './components/admin-work-space-link/admin-work-space-link.component';


@NgModule({
  declarations: [
    NavBarComponent,
    HomeLinkComponent,
    BaseLinkComponent,
    CatalogueLinkComponent,
    ShoppingCartLinkComponent,
    AdminWorkSpaceLinkComponent,
  ],
  exports: [
    NavBarComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule
  ]
})
export class NavBarModule { }

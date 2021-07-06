import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavBarComponent } from './pages/nav-bar/nav-bar.component';
import { HomeLinkComponent } from './components/home-link/home-link.component';
import {HttpClientModule} from '@angular/common/http';
import {RouterModule} from '@angular/router';
import {BaseLinkComponent} from './components/base-link/base-link.component';
import { CatalogueLinkComponent } from './components/catalogue-link/catalogue-link.component';
import { ShoppingCartLinkComponent } from './components/shopping-cart-link/shopping-cart-link.component';
import { ProductCatalogLinkComponent } from './components/product-catalog-link/product-catalog-link.component';
import { WorkspaceLinkComponent } from './components/workspace-link/workspace-link.component';

@NgModule({
  declarations: [
    NavBarComponent,
    HomeLinkComponent,
    BaseLinkComponent,
    CatalogueLinkComponent,
    ShoppingCartLinkComponent,
    ProductCatalogLinkComponent,
    WorkspaceLinkComponent,
  ],
    exports: [
        NavBarComponent,
        ProductCatalogLinkComponent
    ],
    imports: [
        CommonModule,
        HttpClientModule,
        RouterModule
    ]
})
export class NavBarModule { }

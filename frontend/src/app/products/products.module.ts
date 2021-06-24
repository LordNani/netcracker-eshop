import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductComponent } from './product/product.component';
import { ProductListComponent } from './product-list/product-list.component';
import { AppRoutingModule } from '../app-routing.module';
import { ProductCardComponent } from './product-card/product-card.component';

import { ProductSearchComponent } from './product-search/product-search.component';
import { CategoriesPartComponent } from './categories-part/categories-part.component';
import {NgbPaginationModule} from '@ng-bootstrap/ng-bootstrap';


@NgModule({
  declarations: [
    ProductComponent,
    ProductListComponent,
    ProductCardComponent,
    ProductSearchComponent,
    CategoriesPartComponent
  ],
    imports: [
        CommonModule,
        AppRoutingModule,
        NgbPaginationModule
    ]
})
export class ProductsModule { }

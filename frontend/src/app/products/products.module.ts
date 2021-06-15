import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductComponent } from './product/product.component';
import { ProductListComponent } from './product-list/product-list.component';
import { AppRoutingModule } from '../app-routing.module';
import { ProductCardComponent } from './product-card/product-card.component';



@NgModule({
  declarations: [
    ProductComponent,
    ProductListComponent,
    ProductCardComponent,
  ],
  imports: [
    CommonModule,
    AppRoutingModule
  ]
})
export class ProductsModule { }

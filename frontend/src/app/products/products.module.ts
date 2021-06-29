import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductComponent } from './product/product.component';
import { ProductListComponent } from './product-list/product-list.component';
import { AppRoutingModule } from '../app-routing.module';
import { ProductCardComponent } from './product-card/product-card.component';

import { ProductSearchComponent } from './product-search/product-search.component';
import { CategoriesPartComponent } from './categories-part/categories-part.component';
import { ProductManagerComponent } from './product-manager/product-manager.component';
import {ManagerWorkspaceComponent} from './manager-workspace/manager-workspace.component';
import {MatIconModule} from '@angular/material/icon';
import { ProductEditComponent as ProductEditComponent } from './product-edit/product-edit.component';
import {ReactiveFormsModule} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatOptionModule} from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { MatDialogModule } from '@angular/material/dialog';
import {MatRadioModule} from '@angular/material/radio';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatDatepickerModule} from "@angular/material/datepicker";
import {ScrollingModule} from "@angular/cdk/scrolling";
import { ProductCreateComponent } from './product-create/product-create.component';
import {MatPaginatorModule} from "@angular/material/paginator";
import {NgbPaginationModule} from "@ng-bootstrap/ng-bootstrap";

@NgModule({
  declarations: [
    ProductComponent,
    ProductListComponent,
    ProductCardComponent,
    ProductSearchComponent,
    CategoriesPartComponent,
    ProductManagerComponent,
    ManagerWorkspaceComponent,
    ProductEditComponent,
    ProductCreateComponent,
  ],
    imports: [
        CommonModule,
        AppRoutingModule,
        MatIconModule,
        ReactiveFormsModule,
        MatFormFieldModule,
        MatOptionModule,
        MatSelectModule,
        MatDialogModule,
        MatRadioModule,
        BrowserAnimationsModule,
        MatInputModule,
        MatButtonModule,
        MatDatepickerModule,
        ScrollingModule,
        MatPaginatorModule,
        NgbPaginationModule
    ],
  providers: [
    CategoriesPartComponent
  ]
})
export class ProductsModule { }

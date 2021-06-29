import {Component, Input, OnInit, Output, EventEmitter} from '@angular/core';
import {CategoriesPartComponent} from '../categories-part/categories-part.component';
import {ProductService} from '../../_service/product/product.service';
import {Product} from '../../_model/product';
import {Author} from '../../_model/author';
import {AuthorService} from '../../_service/categories/author.service';
import {Language} from '../../_model/Language';
import {CoverType} from '../../_model/cover-type';
import {Genre} from '../../_model/genre';
import {Publisher} from '../../_model/Publisher';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {ProductEditComponent} from '../product-edit/product-edit.component';

@Component({
  selector: 'app-product-manager',
  templateUrl: './product-manager.component.html',
  styleUrls: ['./product-manager.component.css']
})
export class ProductManagerComponent implements OnInit {

  @Input()
  product?: Product;
  @Input()
  author?: Author;
  @Input()
  language?: Language;
  @Input()
  coverType?: CoverType;
  @Input()
  genre?: Genre;
  @Input()
  publisher?: Publisher;
  @Output() editProductEvent = new EventEmitter<string>();
  editComponent = false;


  constructor(private productService: ProductService,
              private authorService: AuthorService,
              private dialog: MatDialog) {
  }

  ngOnInit(): void {

  }

  onChangeStatus(id: number): void {
    if (this.product.productStatus === 'true') {
      this.product.productStatus = 'false';
    } else {
      this.product.productStatus = 'true';
    }

    this.productService.updateProduct(this.product).subscribe(
      res => {
        console.log('update product success');
      },
      error => {
        console.log('update product fail');
      }
    );
  }

  loadEditComponent(): void {
    // this.editComponent = !this.editComponent;
  }

  onCreate(): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '40%';
    dialogConfig.data = {product: this.product};
    this.dialog.open(ProductEditComponent, dialogConfig);
    this.dialog.afterAllClosed.subscribe(() => {
      // Do stuff after the dialog has closed
      this.editProductEvent.emit('update');
      // this.productService.getProduct(this.product.id).subscribe(
      //   res => {
      //     this.product = res;
      //   }
      // );
    });
  }

}

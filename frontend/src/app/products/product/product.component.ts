import {Component, Input, OnInit} from '@angular/core';
import {Product} from '../../_model/product';
import {ProductService} from '../../_service/product/product.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Location} from '@angular/common';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  @Input()
  product?: Product = {} as Product;

  categories?: string[] = [];

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private location: Location
  ) {
  }

  ngOnInit(): void {
    this.getProduct();
    // this.categories = new Map();
    //this.getCategoriesOfProduct();
  }

  getProduct(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    console.log('id', id);
    this.productService.getProduct(id)
      .subscribe(product => {
        this.product = product;
        this.getCategoriesOfProduct();
      }, error => console.log(error));
  }

  getCategoriesOfProduct() {
    this.productService.getCategoriesOfProduct(this.product.author, this.product.coverType, this.product.genre, this.product.language, this.product.publisher)
      .subscribe(categories => {
        this.categories = categories;
      });
  }

  hasDiscount(): boolean {
    if (this.product.productDiscount > 0) {
      return true;
    }
    return false;
  }

  getCategoryValue(key: number): string {
    return this.categories[key];
  }

  getDiscountedPrice(): number{
    return Math.round(this.product.productPrice * ( 1 - (this.product.productDiscount / 100) ));
  }


}



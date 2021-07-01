import {Component, Input, OnInit} from '@angular/core';
import {Product} from '../../_model/product';
import {ProductService} from '../../_service/product/product.service';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';
import {ShoppingCartService} from '../../_service/shopping-cart/shopping-cart.service';

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
    private location: Location,
    private shoppingCartService: ShoppingCartService
  ) {
  }

  ngOnInit(): void {
    this.getProduct();
  }

  getProduct(): void {
    this.shoppingCartService.productInCart = JSON.parse(localStorage.getItem('productInCart'));
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.productService.getProduct(id)
      .subscribe(product => {
        this.product = product;
        this.checkStatus();
        this.getCategoriesOfProduct();
      }, error => console.log(error));
  }

  getCategoriesOfProduct(): void {
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

  getDiscountedPrice(): number {
    return Math.round(this.product.productPrice * (1 - (this.product.productDiscount / 100)));
  }

  addToCart(): void {
    this.shoppingCartService.addProductToCart(this.product);
    this.checkStatus();
  }

  checkStatus(): void {
    const array: Product[] = [this.product];
    this.shoppingCartService.changeStatusToInCart(array);
      // this.shoppingCartService.productInCart.forEach( x => {
      //   if (this.product.id === x.id) {
      //     this.product.productStatus = 'inCard';
      //   }
      // });
  }
}

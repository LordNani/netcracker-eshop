import {Component, OnInit} from '@angular/core';
import {AuthService} from '../_service/auth.service';
import {Product} from '../_model/product';
import {ProductService} from '../_service/product/product.service';
import {ShoppingCartService} from '../_service/shopping-cart/shopping-cart.service';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  newProducts: Product[] = [];

  popularProducts: Product[] = [];
  page = 1;
  size = 4;

  constructor(
    private productService: ProductService,
    private shoppingCartService: ShoppingCartService) {
  }

  ngOnInit(): void {
    this.shoppingCartService.productInCart = JSON.parse(localStorage.getItem('productInCart'));
    this.getNewProducts();
    this.getPopularProducts();
    this.checkStatus();
  }

  getNewProducts(): void {
    this.productService.getNewProducts(this.page, this.size)
      .subscribe(products => {
        this.newProducts = products;
        this.checkStatus();
      });
  }

  getPopularProducts(): void {
    this.productService.getPopularProducts(this.page, this.size)
      .subscribe(products => {
        this.popularProducts = products;
        this.checkStatus();
      });
  }


  addProductToCart(product: Product): void {
    // this.shoppingCartService.addProductToCart(product);
    this.checkStatus();
  }

  checkStatus(): void {
    this.shoppingCartService.changeStatusToInCart(this.newProducts);
    // this.newProducts.forEach( element => {
    //   this.shoppingCartService.productInCart.forEach( x => {
    //     if (element.id === x.id) {
    //       element.productStatus = 'inCard';
    //     }
    //   });
    // });

    this.shoppingCartService.changeStatusToInCart(this.popularProducts);
    // this.popularProducts.forEach( element => {
    //   this.shoppingCartService.productInCart.forEach( x => {
    //     if (element.id === x.id) {
    //       element.productStatus = 'inCard';
    //     }
    //   });
    // });
  }
}

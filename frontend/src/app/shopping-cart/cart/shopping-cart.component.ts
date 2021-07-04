import {Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {Product} from '../../_model/product';
import {ShoppingCartService} from '../../_service/shopping-cart/shopping-cart.service';
import {AlertService} from '../../_service/alert/alert.service';
import {Router} from '@angular/router';
import {AuthService} from '../../_service/auth/auth.service';
import {ErrorMessages} from '../../_model/labels/error.messages';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit{


  constructor(private router: Router,
              private shoppingCartService: ShoppingCartService,
              private alertService: AlertService,
              private authService: AuthService) {
  }

  total = 0;
  totalWithDiscount = 0;
  products: Product[] = [];
  amount = 1;
  userId = -1;
  productsWithErrors: Product[] = [];
  countError = false;

  ngOnInit(): void {
    this.products = JSON.parse(localStorage.getItem('productInCart'));
    if (localStorage.getItem('idUser') !== null) {
      this.userId = JSON.parse(localStorage.getItem('idUser'));
    }
    this.totalPrice();
  }

  totalPrice(): void {
    this.total = 0;
    this.totalWithDiscount = 0;
    this.products.forEach(x => {
      this.total += x.productPrice * x.productAmount;
      this.totalWithDiscount += Math.round(x.productPrice * (1 - (x.productDiscount / 100)) * x.productAmount);
    });
  }

  updatePrice($event: string): void {
    localStorage.setItem('productInCart', JSON.stringify(this.products));
    this.totalPrice();
  }

  getOrderedProducts(value: any): void {
    this.products = this.products.slice().reverse();
  }

  removeProduct($event: Product): void {
    const num = this.products.find(x => x.id === $event.id);
    const index = this.products.indexOf(num);
    if (index > -1) {
      this.products.splice(index, 1);
      localStorage.setItem('productInCart', JSON.stringify(this.products));
    }
    this.totalPrice();
  }

  confirmOrder(): void {
    const checkProducts: Array<Product> = [];
    this.products.forEach((x, index) => {
      if (x.productStatus !== 'ACTIVE') {
        this.countError = true;
      }
      checkProducts.push(new Product());
      checkProducts[index].id = x.id;
      checkProducts[index].productAmount = x.productAmount;
      checkProducts[index].productPrice = Math.round(x.productPrice * (1 - (x.productDiscount / 100)) * x.productAmount);
    });

    this.shoppingCartService.getOrderConfirm(checkProducts, this.userId).subscribe(
      res => {
        localStorage.setItem('idUser', res);
        this.router.navigateByUrl('/');
        this.countError = false;
      },
      error => {
        window.scroll(0, 0);
        this.productsWithErrors = error.error.problemList;
        this.alertService.error(ErrorMessages[error.error.message], {autoClose: false});
        this.countError = true;
      }
    );
    this.ngOnInit();
  }

  changeCountError($event: any): void {
    this.products.forEach(x => {
      console.log(x.productStatus);
    });
    this.countError = $event;
  }

}

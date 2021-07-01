import {Component, OnInit} from '@angular/core';
import {Product} from '../../_model/product';
import {ShoppingCartService} from '../../_service/shopping-cart/shopping-cart.service';
import {AlertService} from '../../_service/alert.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {


  constructor(private router: Router,
              private shoppingCartService: ShoppingCartService,
              private alertService: AlertService) {
  }

  total = 0;
  totalWithDiscount = 0;
  products: Product[] = [];
  amount = 1;
  productsWithBadAmount: Product[] = [];

  ngOnInit(): void {
    this.products = JSON.parse(localStorage.getItem('productInCart'));
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
      checkProducts.push(new Product());
      checkProducts[index].id = x.id;
      checkProducts[index].productAmount = x.productAmount;
    });

    this.shoppingCartService.getOrderConfirm(checkProducts).subscribe(
      res => {
        this.router.navigateByUrl('/manager');
      },
      error => {
        window.scroll(0, 0);
        this.productsWithBadAmount = error.error.problemList;
        this.alertService.error(error.error.message, {autoClose: false});
      }
    );
  }
}

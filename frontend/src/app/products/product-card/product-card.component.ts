import {Component, Input, OnInit} from '@angular/core';
import {Product} from '../../_model/product';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent implements OnInit {

  @Input()
  product?: Product;

  constructor() {
  }

  ngOnInit(): void {
  }

  hasDiscount(): boolean {
    if (this.product.productDiscount > 0) {
      return true;
    }
    return false;
  }

  getDiscountedPrice(): number{
    return Math.round(this.product.productPrice * ( 1 - (this.product.productDiscount / 100) ));
  }
}

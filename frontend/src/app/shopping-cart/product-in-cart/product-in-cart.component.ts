import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from '@angular/core';
import {Product} from '../../_model/product';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-product-in-cart',
  templateUrl: './product-in-cart.component.html',
  styleUrls: ['./product-in-cart.component.css']
})
export class ProductInCartComponent implements OnInit, OnChanges {

  @Input() product: Product;
  @Input() productsWithBadAmount: Product[];
  @Output() updateAmount = new EventEmitter<string>();
  @Output() remove = new EventEmitter<Product>();
  amount: number;
  form: FormGroup;
  priceWithDiscount: number;
  countErrorMessage = 'Count must be greater than 0';
  ErrorMessage: string;
  storeAmountProblem: boolean;

  constructor(private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.ErrorMessage = 'Not enough product in stock, please remove productCount products';
    this.storeAmountProblem = false;
    this.amount = this.product.productAmount;
    this.initForm();
    this.priceWithDiscount = Math.round(this.product.productPrice * ( 1 - (this.product.productDiscount / 100) ) * this.amount);
    this.checkAmount();
  }

  private initForm(): void {
    this.form = this.formBuilder.group({
      productAmount: new FormControl(this.amount, [Validators.required,
        Validators.pattern('^[1-9][0-9]*$')])
    });
  }

  onChange($event: Event): void {
    this.amount = this.form.value.productAmount;
    this.storeAmountProblem = false;
    this.priceWithDiscount = Math.round(this.product.productPrice * ( 1 - (this.product.productDiscount / 100)) * this.amount);
    this.product.productAmount = this.amount;
    this.updateAmount.emit('updatePrice');
  }

  removeProduct(): void {
    this.remove.emit(this.product);
  }

  ngOnChanges(): void {
    this.ngOnInit();
  }

  checkAmount(): void {
    const num = this.productsWithBadAmount.find(x => x.id === this.product.id);
    if (num !== undefined) {
      this.storeAmountProblem = true;
      this.ErrorMessage = this.ErrorMessage.replace('productCount', String(num.productAmount));
    }
  }
}

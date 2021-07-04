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
  @Input() productsWithErrors: Product[];
  @Output() updateAmount = new EventEmitter<string>();
  @Output() remove = new EventEmitter<Product>();
  @Output() countError = new EventEmitter<boolean>();
  amount: number;
  form: FormGroup;
  priceWithDiscount: number;
  countErrorMessage = 'Count must be integer and greater than 0';
  stockErrorMessage: string;
  storeAmountProblem: boolean;
  productStatusError: boolean;

  constructor(private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    const a = this.productsWithErrors;
    this.stockErrorMessage = 'Not enough product in stock, please remove productCount products';
    this.storeAmountProblem = false;
    this.productStatusError = false;
    this.amount = this.product.productAmount;
    this.initForm();
    this.priceWithDiscount = Math.round(this.product.productPrice * (1 - (this.product.productDiscount / 100)) * this.amount);
    this.checkAmount();
    this.countError.emit(this.form.get('productAmount')?.invalid);
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
    this.priceWithDiscount = Math.round(this.product.productPrice * (1 - (this.product.productDiscount / 100)) * this.amount);
    this.product.productAmount = this.amount;
    this.countError.emit(this.form.get('productAmount')?.invalid);
    this.updateAmount.emit('updatePrice');
  }

  removeProduct(): void {
    this.remove.emit(this.product);
    this.countError.emit(this.form.get('productAmount')?.invalid);
  }

  ngOnChanges(): void {
    this.ngOnInit();
  }

  checkAmount(): void {
    if (this.productsWithErrors) {
      const num = this.productsWithErrors.find(x => x.id === this.product.id);
      if (num !== undefined) {
        if (num.productStatus !== 'ACTIVE') {
          this.productStatusError = true;
        }
        if (num.productAmount >= 0) {
          this.storeAmountProblem = true;
          this.stockErrorMessage = this.stockErrorMessage.replace('productCount', String(num.productAmount));
        }
      }
    }
  }
}

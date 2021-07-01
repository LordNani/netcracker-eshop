import {Component, Input, EventEmitter, Output} from '@angular/core';
import {Product} from '../../_model/product';

@Component({
  selector: 'app-temp-product',
  templateUrl: './temp-product.component.html',
  styleUrls: ['./temp-product.component.css']
})
export class TempProductComponent{

  toggle = true;
  status = 'Enable';

  constructor() { }

  @Input() product: Product;
  @Output() openModal = new EventEmitter<{product: Product, mode: string}>();

  enableDisableRule(): void {
    this.toggle = !this.toggle;
    this.status = this.toggle ? 'Enable' : 'Disable';
  }

  onOpenModal(product: Product, mode: string): void{
    this.openModal.emit({product, mode});
  }

}

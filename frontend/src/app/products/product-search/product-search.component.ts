import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Observable, Subject} from 'rxjs';
import {
  debounceTime, distinctUntilChanged, switchMap
} from 'rxjs/operators';
import {Product} from '../../_model/product';
import {ProductService} from '../../_service/product/product.service';


@Component({
  selector: 'app-product-search',
  templateUrl: './product-search.component.html',
  styleUrls: ['./product-search.component.css']
})
export class ProductSearchComponent implements OnInit {

  @Output() searchValue = new EventEmitter<string>();
  @Output() cancelFilters = new EventEmitter();

  private searchTerms = new Subject<string>();

  constructor() {
  }

  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
  }

  onSearchClick(value: string){
    this.searchValue.emit(value);
  }

  onCancelFiltersClick(){
    this.cancelFilters.emit();
  }

}

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

  products$: Observable<Product[]>;
  private searchTerms = new Subject<string>();

  constructor(private productService: ProductService) {
  }

// Push a search term into the observable stream.
  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    this.products$ = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(500),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((term: string) => this.productService.searchProducts(term)),
    );
  }

  onSearchClick(value: string){
    // if (value.length<=3){
    //   console.log("small");
    // }
    this.searchValue.emit(value);
  }

  onCancelFiltersClick(){
    this.cancelFilters.emit();
  }

}

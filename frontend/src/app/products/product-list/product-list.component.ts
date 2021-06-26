import {Component, OnInit, Pipe} from '@angular/core';
import {ProductCategory} from '../../_model/productCategory';
import {ProductCategoryService} from '../../_service/product-category/product-category.service';
import {Product} from '../../_model/product';
import {ProductService} from '../../_service/product/product.service';
import {typesOfCategories} from '../../_model/typesOfCategories';
import {Filters} from '../../_model/filters';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})


export class ProductListComponent implements OnInit {

  page = 1;
  size = 6;
  amountOfProducts: number;
  currentProducts: Product[] = [];
  searchValue: string;
  filtersValue: Filters;
  orderValue: string;


  constructor(
    private productService: ProductService,
  ) {
  }

  ngOnInit(): void {
    this.getProducts();
    this.searchValue = '';
    this.filtersValue = {author: [], coverType: [], genre: [], language: [], publisher: []} as Filters;
    this.orderValue = '';
    this.getAmountOfProducts();
  }

  getProducts(): void {
    this.productService.getAllProducts(this.page, this.size)
      .subscribe(products => {
        this.currentProducts = products;
      });
    this.getAmountOfProducts();
  }

  onPageChange(currentPage: number): void{
    this.page = currentPage;
    this.getSearchedOrderedFilteredProducts();
  }

  getOrderedProducts(value: string): void{
    this.orderValue = value;
    this.getSearchedOrderedFilteredProducts();
    // console.info('orderValue ', this.orderValue, ' searchvalue ', this.searchValue, ' filters ', JSON.stringify(this.filtersValue));
    // this.productService.orderProducts(this.page, this.size, value)
    //   .subscribe(products =>{
    //     this.currentProducts = products;
    //   });

  }

  getSearchedProducts(value: string): void{
    if (value !== ''){
      this.searchValue = value;
      this.getSearchedOrderedFilteredProducts();
      // console.info('orderValue ', this.orderValue, ' searchvalue ', this.searchValue, ' filters ', JSON.stringify(this.filtersValue));
      // this.productService.searchProducts(value)
      //   .subscribe(products => {
      //     // console.log('inside subscribe');
      //     // console.log(products);
      //     this.currentProducts = products;
      //   });
    }
    // else this.getProducts();
    else { this.searchValue = ''; }
  }

  getFilteredProducts(filters: Filters): void{
    this.filtersValue = filters;
    // console.info('orderValue ', this.orderValue, ' searchvalue ', this.searchValue, ' filters ', JSON.stringify(this.filtersValue));
    // this.productService.filterProducts(this.page, this.size, filters)
    //   .subscribe(products => {
    //      // console.log('getFilteredProducts inside subscribe');
    //      //console.log(products);
    //     this.currentProducts = products;
    //   });
    this.getSearchedOrderedFilteredProducts();
  }

  getSearchedOrderedFilteredProducts(): void{
    this.productService.searchOrderFilterProducts(this.page, this.size, this.searchValue, this.orderValue, this.filtersValue)
      .subscribe(products => {
        this.currentProducts = products;
      });
    this.getAmountOfProducts();
  }

  cancelFilters(): void{
    window.location.reload();
  }

  getAmountOfProducts(): void{
    this.productService.getProductsCount(this.searchValue, this.orderValue, this.filtersValue)
      .subscribe(numb => {
        this.amountOfProducts = numb;
      });
  }
}

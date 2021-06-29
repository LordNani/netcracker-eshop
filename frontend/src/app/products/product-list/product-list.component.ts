import {Component, OnInit, Pipe} from '@angular/core';
import {ProductCategory} from "../../_model/productCategory";
import {ProductCategoryService} from "../../_service/product-category/product-category.service";
import {Product} from "../../_model/product";
import {ProductService} from "../../_service/product/product.service";
import {typesOfCategories} from "../../_model/typesOfCategories";
import {Filters} from "../../_model/filters";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})


export class ProductListComponent implements OnInit {

  page: number = 1;
  size: number = 6;
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
    this.searchValue = "";
    this.filtersValue = {author: [], coverType: [], genre: [], language: [], publisher: []} as Filters;
    this.orderValue = '';
    this.getAmountOfProducts();
  }

  getProducts(): void {
    this.productService.getAllProducts(this.page, this.size)
      .subscribe(products => {
        this.currentProducts = products;
      });
  }

  getOrderedProducts(value: string){
    this.orderValue = value;
    this.getSearchedOrderedFilteredProducts();
  }

  getSearchedProducts(value: string){
    if (value != ''){
      this.searchValue = value;
      this.getSearchedOrderedFilteredProducts();
    }
    else this.searchValue = '';
  }

  getFilteredProducts(filters: Filters): void{
    this.filtersValue = filters;
    this.getSearchedOrderedFilteredProducts();
  }

  getSearchedOrderedFilteredProducts(): void{
    this.productService.searchOrderFilterProducts(this.page, this.size, this.searchValue, this.orderValue, this.filtersValue)
      .subscribe(products => {
        this.currentProducts = products;
      });
    this.getAmountOfProducts();
  }

  onPageChange(currentPage: number): void{
    this.page = currentPage;
    this.getSearchedOrderedFilteredProducts();
  }

  cancelFilters(): void{
    window.location.reload();
  }

  getAmountOfProducts(){
    this.productService.getProductsCount(this.searchValue, this.orderValue, this.filtersValue)
      .subscribe(numb => {
        this.amountOfProducts = numb;
      });
  }
}

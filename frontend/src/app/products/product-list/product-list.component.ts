import {Component, OnInit} from '@angular/core';
import {ProductCategory} from "../../_model/productCategory";
import {ProductCategoryService} from "../../_service/product-category/product-category.service";
import {Product} from "../../_model/product";
import {ProductService} from "../../_service/product/product.service";
import {element} from "protractor";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  page: number = 1;
  size: number = 50;
  productCategories: ProductCategory[] = [];
  allProducts: Product[] = [];
  currentProducts: Product[] = [];

  constructor(
    private productCategoryService: ProductCategoryService,
    private productService: ProductService,
  ) {
  }

  filter(event, id: number): void {
/*
    //if (event.target.change){
    this.currentProducts.length = 0;
    for (let i of this.allProducts) {
      if (i.productCategory === id) {
        this.currentProducts.push();
        console.log("pushed")
      }
    }

    console.log("here")

    console.log(event);

 */
  }

  ngOnInit(): void {
    this.getProductCategories();
    this.getProducts();
  }

  getProductCategories(): void {
    this.productCategoryService.getAllProductCategories()
      .subscribe(productCategories => this.productCategories = productCategories);
  }

  getProducts(): void {
    this.productService.getAllProducts(1, 50)
      .subscribe(products => {
        this.allProducts = products;
        this.currentProducts = this.allProducts;
      });


  }

}

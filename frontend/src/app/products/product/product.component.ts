import {Component, Input, OnInit} from '@angular/core';
import {Product} from "../../_model/product";
import {ProductService} from "../../_service/product/product.service";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  @Input()
  product?: Product;

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private location: Location
  ) {
  }

  ngOnInit(): void {
    this.getProduct();
    // console.log(this.product.id)
  }

  getProduct(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.productService.getProduct(id)
      .subscribe(product => {
        console.log(product);
        this.product = product
      }, error => console.log(error));

    // this.product=
    //   {id: 1, productName: 'product 1', productAmount: 2, productPrice: 100,
    //   productDiscount: 0, productDate: new Date("2021-06-02"), productDescription: 'Description',
    //   productStatus: 'T', genre: 0, author: 0, coverType: 0, language: 0, publisher: 0} as Product;
  }

  goBack(): void {
    this.location.back();
  }

}

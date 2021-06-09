import {Component, OnInit} from '@angular/core';
import {Product} from '../_model/product';
import {ProductService} from '../_service/product.service';
import {HttpErrorResponse, HttpParams} from '@angular/common/http';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-product-catalog',
  templateUrl: './product-catalog.component.html',
  styleUrls: ['./product-catalog.component.css']
})
export class ProductCatalogComponent implements OnInit {

  public date = new Date();

  constructor(private productService: ProductService) {
  }

  products: Product[];
  items = [];
  pageOfItems: Array<any>;
  editProduct: Product;
  filtersCategory = new Array<string>();
  filtersPrice = new Array<string>();
  orderBy = 'name';

  ngOnInit(): void {
    this.getProduct(1, 5);
  }

  onChangePage(pageOfItems: Array<any>): void {
    // update current page of items
    this.pageOfItems = pageOfItems;
  }

  public getProduct(page: number, size: number): void {
    let params = new HttpParams();
    params = params.append('sortBy', this.orderBy);
    params = params.append('categoryFilter', this.filtersCategory.join(', '));
    params = params.append('priceFilter', this.filtersPrice.join(', '));
    console.log(params.toString());

    // if (this.filtersCategory.length === 0 && this.filtersPrice.length === 0 && this.orderBy === 'name') {
    this.productService.getProduct(1, 5).subscribe(
        (response: Product[]) => {
          this.products = response;
          console.log(this.products);
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    // }
    // } else {
    //   this.productService.getProductSortFilter(params).subscribe(
    //     (response: Product[]) => {
    //       console.log('response' + response);
    //       this.products = response;
    //     },
    //     (error: HttpErrorResponse) => {
    //       alert(error.message);
    //     }
    //   );
    // }

  }

  public onAddProduct(addForm: NgForm): void {
    console.log(addForm.value);
    document.getElementById('add-product-form').click();
    this.productService.addProduct(addForm.value).subscribe(
      (response: Product) => {
        console.log(response);
        // this.getProduct();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onUpdateProduct(product: Product): void {
    console.log(product);
    this.productService.updateProduct(product).subscribe(
      (response: Product) => {
        console.log(response);
        // this.getProduct();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onOpenModal(product: Product, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addProductModal');
    }
    if (mode === 'edit') {
      this.editProduct = product;
      button.setAttribute('data-target', '#editProductModal');
    }
    if (mode === 'remove') {
      button.setAttribute('data-target', '#removeProductModal');
    }
    container.appendChild(button);
    button.click();
  }

  changeSort(event): void {
    this.orderBy = event.target.value;
    console.log(this.orderBy);
    // this.getProduct();
  }

  chooseFilerCategory(event): void {
    const filterCategory = event.target.value;
    if (this.filtersCategory.find(x => x === filterCategory) === undefined) {
      this.filtersCategory.push(filterCategory);
    } else {
      this.filtersCategory.splice(this.filtersCategory.indexOf(filterCategory), 1);
    }
    console.log(this.filtersCategory);
    // this.getProduct();
  }

  chooseFilerPrice(event): void {
    const filter = event.target.value;
    if (this.filtersPrice.find(x => x === filter) === undefined) {
      this.filtersPrice.push(filter);
    } else {
      this.filtersPrice.splice(this.filtersPrice.indexOf(filter), 1);
    }
    console.log(this.filtersPrice);
    // this.getProduct();
  }

  searchProduct(key: string): void {
    console.log(key);
    const results: Product[] = [];
    for (const product of this.products) {
      if (product.productName.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || product.productCategory.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || product.productDescription.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(product);
      }
    }
    this.products = results;
    if (results.length === 0 || !key) {
      // this.getProduct();
    }
  }
}

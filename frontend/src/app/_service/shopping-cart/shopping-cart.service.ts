import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable, of, throwError} from 'rxjs';
import {Product} from '../../_model/product';
import {catchError, first} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {

  constructor(private http: HttpClient) {
  }

  public productInCart: Array<Product> = [];
  private productsUrl = 'http://localhost:8081/shopping-cart';

  getOrderConfirm(products: Array<Product>, userId: number): Observable<any> {
    const params = new HttpParams().set('products', JSON.stringify(products))
      .set('userId', String(userId));
    const url = `${this.productsUrl}/create-order`;
    return this.http.get<any>(url, {params});
  }

  addProductToCart(value: Product): void {
    const product: Product = Object.assign({}, value);
    if (localStorage.getItem('productInCart') !== null) {
      this.productInCart = JSON.parse(localStorage.getItem('productInCart'));
      if (!(this.productInCart.filter(x => x.id === product.id).length > 0)) {
        product.productAmount = 1;
        this.productInCart.push(product);
      }
    } else {
      this.productInCart = [];
      product.productAmount = 1;
      this.productInCart.push(product);
    }
    localStorage.setItem('productInCart', JSON.stringify(this.productInCart));
  }

  changeStatusToInCart(value: Product[]): void {
    if (value) {
      value.forEach(element => {
        this.productInCart.forEach(x => {
          if (element.id === x.id) {
            element.productStatus = 'inCard';
          }
        });
      });
    }
  }

}

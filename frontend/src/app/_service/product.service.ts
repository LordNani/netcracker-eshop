import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {Product} from '../_model/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private allProductUrl = 'http://localhost:8081/product/get-all';
  private allProductSortUrl = 'http://localhost:8081/catalog/product/sort';
  private allProductFilterUrl = 'http://localhost:8081/catalog/product/filter';
  private addProductUrl = 'http://localhost:8081/product/add';
  private updateProductUrl = 'http://localhost:8081/product/update';
  private removeProductUrl = 'http://localhost:8081/product/remove';
  private productSortFilterUrl = 'http://localhost:8081/catalog/product/sort-filter';

  constructor(private http: HttpClient,
              private router: Router) {
  }

  public getProduct(page: number, size: number): Observable<Product[]> {
    const params = new HttpParams().set('page', String(page)).set('size', String(size));
    return this.http.get<Product[]>(this.allProductUrl, {params});
  }

  public addProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(this.addProductUrl, product);
  }

  public updateProduct(product: Product): Observable<Product> {
    return this.http.put<Product>(this.updateProductUrl, product);
  }

  public getProductSortBy(sortBy: string): Observable<Product[]> {
    return this.http.get<Product[]>(this.allProductSortUrl + '?sortBy=' + sortBy);
  }

  public getProductFilterBy(params: HttpParams): Observable<Product[]> {
    return this.http.get<Product[]>(this.allProductFilterUrl, {params});
  }

  public getProductSortFilter(params: HttpParams): Observable<Product[]> {
    return this.http.get<Product[]>(this.productSortFilterUrl, {params});
  }

}

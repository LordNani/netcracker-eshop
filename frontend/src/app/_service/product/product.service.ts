import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {catchError, map, tap} from 'rxjs/operators';
import {Observable, of} from 'rxjs';
import {Product} from '../../_model/product';

import {PRODUCTS} from '../../_utils/products';
import {Filters} from '../../_model/filters'; // удалить, когда продукты будут браться с бэка

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(
    private http: HttpClient,
  ) {
  }

  private productsUrl = 'http://localhost:8081/product';


  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  getAllProducts(page: number, size: number): Observable<Product[]> {
    const url = `${this.productsUrl}/get-all?page=${page}&size=${size}`;
    return this.http.get<Product[]>(url)
      .pipe(
        catchError(this.handleError<Product[]>('getAllProducts', []))
      );
  }

  getProduct(id: number): Observable<Product> {
    const url = `${this.productsUrl}/get-by-id/${id}`;
    return this.http.get<Product>(url)
      .pipe(
        catchError(this.handleError<Product>(`getProduct id=${id}`))
      );
  }

  updateProduct(product: Product): Observable<any> {
    const url = `${this.productsUrl}/update`;
    return this.http.put(url, product);
  }

  addProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(this.productsUrl, product, this.httpOptions)
      .pipe(
        catchError(this.handleError<Product>('addProduct'))
      );
  }

  searchProducts(term: string): Observable<Product[]> {
    if (!term.trim()) {
      return of([]);
    }
    const url = `${this.productsUrl}/get-by-name/${term}`;
    return this.http.get<Product[]>(url)
      .pipe(
        catchError(this.handleError<Product[]>('getProductsByName', []))
      );
  }

  filterProducts(page: number, size: number, filters: Filters): Observable<Product[]> {
    // let params = new HttpParams().set("filters", encodeURIComponent(JSON.stringify(filters)));
    // const url = `${this.productsUrl}/get-all-filtered?page=${page}&size=${size}&filters=${JSON.stringify(filters)}`;
    const params = new HttpParams().set('page', String(page)).set('size', String(size)).set('filters', JSON.stringify(filters));
    const url = `${this.productsUrl}/get-all-filtered`;

    return this.http.get<Product[]>(url, {params});
  }

  orderProducts(page: number, size: number, orderBy: string): Observable<Product[]> {
    const url = `${this.productsUrl}/get-all-order`;
    const params = new HttpParams().set('page', String(page)).set('size', String(size)).set('orderBy', orderBy);
    return this.http.get<Product[]>(url, {params})
      .pipe(
        catchError(this.handleError<Product[]>('orderProducts', []))
      );
  }

  searchOrderFilterProducts(page: number, size: number, searchValue: string, orderValue: string, filters: Filters): Observable<Product[]>{
    const params = new HttpParams().set('page', String(page)).set('size', String(size)).set('search', searchValue).set('orderBy', orderValue).set('filters', JSON.stringify(filters));
    const url = `${this.productsUrl}/get-all-searched-ordered-filtered`;
    return this.http.get<Product[]>(url, {params})
      .pipe(
        catchError(this.handleError<Product[]>('searchOrderFilterProducts', []))
      );
  }

  getProductsCount(searchValue: string, orderValue: string, filters: Filters): Observable<number>{
    const params = new HttpParams().set('search', searchValue).set('orderBy', orderValue).set('filters', JSON.stringify(filters));
    const url = `${this.productsUrl}/get-number-of-searched-ordered-filtered`;
    return this.http.get<number>(url, {params})
      .pipe(
        catchError(this.handleError<number>('getProductsCount', ))
      );
  }

  getCategoriesOfProduct(author: number, coverType: number, genre: number, language: number, publisher: number): Observable<string[]>{
    const params = new HttpParams().set('author', String(author)).set('cover-type', String(coverType)).set('genre', String(genre)).set('language', String(language)).set('publisher', String(publisher));
    const url = `${this.productsUrl}/get-categories-of-product`;
    return this.http.get<string[]>(url, {params})
      .pipe(
        catchError(this.handleError<string[]>('getProductsCount', ))
      );
  }

  getPopularProducts(page: number, size: number): Observable<Product[]> {
    const url = `${this.productsUrl}/get-popular`;
    const params = new HttpParams().set('page', String(page)).set('size', String(size));
    return this.http.get<Product[]>(url, {params})
      .pipe(
        catchError(this.handleError<Product[]>('getPopularProducts', []))
      );
  }

  getNewProducts(page: number, size: number): Observable<Product[]> {
    const url = `${this.productsUrl}/get-new`;
    const params = new HttpParams().set('page', String(page)).set('size', String(size));
    return this.http.get<Product[]>(url, {params})
      .pipe(
        catchError(this.handleError<Product[]>('getNewProducts', []))
      );
  }
}

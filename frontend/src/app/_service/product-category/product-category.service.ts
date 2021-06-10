import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, map, tap} from 'rxjs/operators';
import {Observable, of} from "rxjs";
import {ProductCategory} from "../../_model/productCategory";

import {PRODUCT_CATEGORIES} from "../../_utils/productCategories";  //удалить, когда категории будут браться с бэка

@Injectable({
  providedIn: 'root'
})
export class ProductCategoryService {

  private productCategoriesUrl = 'localhost:8081/api/productCategories';

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  getAllProductCategories(): Observable<ProductCategory[]> {
    /*
    return this.http.get<ProductCategory[]>(this.productCategoriesUrl)
      .pipe(
        catchError(this.handleError<ProductCategory[]>('getAllProductCategories', []))
      );
    */
    //удалить, когда продукты будут браться с бэка:
    const productCategories = of(PRODUCT_CATEGORIES);
    return productCategories;
  }

  getProductCategory(id: number): Observable<ProductCategory> {
    const url = `${this.productCategoriesUrl}/${id}`;
    return this.http.get<ProductCategory>(url)
      .pipe(
        catchError(this.handleError<ProductCategory>(`getProductCategory id=${id}`))
      )
  }

  updateProductCategory(productCategory: ProductCategory): Observable<any> {
    return this.http.put(this.productCategoriesUrl, productCategory, this.httpOptions)
      .pipe(
        catchError(this.handleError<any>('updateProductCategory'))
      );
  }

  addProductCategory(productCategory: ProductCategory): Observable<ProductCategory> {
    return this.http.post<ProductCategory>(this.productCategoriesUrl, productCategory, this.httpOptions)
      .pipe(
        catchError(this.handleError<ProductCategory>('addProductCategory'))
      );
  }

  deleteProductCategory(id: number): Observable<ProductCategory> {
    const url = `${this.productCategoriesUrl}/${id}`;
    return this.http.delete<ProductCategory>(url, this.httpOptions)
      .pipe(
        catchError(this.handleError<ProductCategory>('deleteProductCategory'))
      );
  }

  searchProductCategories(term: string): Observable<ProductCategory[]> {
    if (!term.trim()) {
      return of([]);
    }
    return this.http.get<ProductCategory[]>(`${this.productCategoriesUrl}/?name=${term}`)
      .pipe(
        catchError(this.handleError<ProductCategory[]>('searchProductCategories', []))
      );
  }


  constructor(
    private http: HttpClient,
  ) {
  }
}

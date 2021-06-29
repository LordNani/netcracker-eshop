import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Publisher} from '../../_model/Publisher';
import {HttpClient} from '@angular/common/http';
import {Product} from "../../_model/product";

@Injectable({
  providedIn: 'root'
})
export class OrderDetailsService {

  constructor(private http: HttpClient) { }

  private orderId = 0;
  private publisherUrl = 'http://localhost:8081/order-history/details';

  setOrderId(id: number): void {
    this.orderId = id;
  }

  getOrderId(): number {
    return this.orderId;
  }

  getAllProductInOrder(orderId: number): Observable<Product[]> {
    const url = `${this.publisherUrl}/${orderId}`;
    return this.http.get<Product[]>(url);
  }

  getCountOfProduct(orderId: number): Observable<Product[]> {
    const url = `${this.publisherUrl}/count-of-product/${orderId}`;
    return this.http.get<Product[]>(url);
  }
}

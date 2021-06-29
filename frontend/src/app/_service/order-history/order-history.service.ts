import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../../_model/user';
import {OrderCard} from '../../_model/orderCard';

@Injectable({
  providedIn: 'root'
})
export class OrderHistoryService {

  constructor(private http: HttpClient) { }

  private orderHistoryUrl = 'http://localhost:8081/order-history';

  getAllOrderById(id: number, page: number, size: number): Observable<OrderCard[]> {
    const url = `${this.orderHistoryUrl}/?id=${id}&page=${page}&size=${size}`;
    return this.http.get<OrderCard[]>(url);
  }

  getOrderCount(id: number): Observable<number> {
    const url = `${this.orderHistoryUrl}/count/${id}`;
    return this.http.get<number>(url);
  }
}

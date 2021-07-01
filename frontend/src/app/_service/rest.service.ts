import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Managers} from '../_model/managers';
import {Observable, of} from 'rxjs';
import * as _ from 'lodash';

import { catchError, map, tap } from 'rxjs/operators';
import {User} from "../_model/user";
@Injectable({
  providedIn: 'root'

})
export class RestService {
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  // firstName: string;
  // lastName: string;
  // email: string;
  // phoneNumber: string;
  // role: string;
  managers: Managers [] = [];
  users: User [] = [];
  constructor(private http: HttpClient) { }
  private addNewUrl = 'http://localhost:8081/search/new';
  private getAllUrl = 'http://localhost:8081/search/all';
  private deleteUrl = 'http://localhost:8081/search/delete';
  private updateUrl = 'http://localhost:8081/search/edit'
  url = 'http://localhost:8081/admin/search/';
  getManagers(): Observable<any> {
    return this.http.get<User[]>(this.getAllUrl);
  }
  addMember(user: User): Observable<any>{
    // return this.http.post<Managers>(this.url, manager);
    console.log(user);
    return this.http.post<User>(this.addNewUrl, user);
  }
  deleteUser(id: number): Observable<any>{
    return this.http.delete<User>(this.deleteUrl + '/' + id);
  }

  updateUser(id: number, user: User): Observable<any>{
    return this.http.put<User>(this.deleteUrl + '/' + id, user);
  }

  getManager(): Observable<any> {
    const TUrll = 'http://localhost:8081/admin/search/manager';
    const nUrl = `${TUrll}`;
    return this.http.get(nUrl);
  }
  getCorier(): Observable<any> {
    const TUrll = 'http://localhost:8081/admin/search/courier';
    const nUrl = `${TUrll}`;
    return this.http.get(nUrl);
  }
  getByName(name): Observable<any> {
    const TUrll = 'http://localhost:8081/admin/getByName/' + name ;
    const nUrl = `${TUrll}`;
    return this.http.get(nUrl);
  }
  getOnDutyNow(): Observable<any> {
    const TUrll = 'http://localhost:8081/admin/onDuty';
    const nUrl = `${TUrll}`;
    return this.http.get(nUrl);
  }
}

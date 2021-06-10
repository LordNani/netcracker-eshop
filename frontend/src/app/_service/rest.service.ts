import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Managers} from '../_model/managers';
import {Observable, of} from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
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
  managers: Managers;
  constructor(private http: HttpClient) { }
  url = 'http://localhost:3000/Managers';
  getManagers(): Observable<any> {
    return this.http.get<Managers[]>(this.url);
  }
  addMember(manager): Observable<Managers>{
    return this.http.post<Managers>(this.url, manager);

  }
  deleteUser(id: number): Observable<Managers>{
    return this.http.delete<Managers>(this.url + '/' + id);
  }
}

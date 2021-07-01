import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {Product} from '../../_model/product';
import {catchError} from 'rxjs/operators';
import {User} from '../../_model/user';

@Injectable({
  providedIn: 'root'
})
export class SettingsService {

  constructor(private http: HttpClient) { }

  private settingsUrl = 'http://localhost:8081/settings';

  getUserByLogin(login: string): Observable<User> {
    const url = `${this.settingsUrl}/info/${login}`;
    return this.http.get<User>(url);
  }

  updateUser(user: User): Observable<User> {
    const url = `${this.settingsUrl}/info/update`;
    return this.http.put<User>(url, user);
  }

}

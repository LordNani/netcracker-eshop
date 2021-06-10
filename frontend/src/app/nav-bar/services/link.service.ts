import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LinkService {

  constructor(private http: HttpClient) { }
  getLink(url: string): Observable<any>{
    return this.http.get('assets/json/links/' + url + '.json');
  }
}

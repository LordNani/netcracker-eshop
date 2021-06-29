import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';
import {Publisher} from '../../_model/Publisher';
import {CoverType} from '../../_model/cover-type';

@Injectable({
  providedIn: 'root'
})
export class PublisherService {

  constructor(private http: HttpClient) { }

  private publisherUrl = 'http://localhost:8081/publisher';
  private publisherList: Publisher[] = [];

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

  getAllPublishers(): Observable<Publisher[]> {
    const url = `${this.publisherUrl}/get-all`;
    return this.http.get<Publisher[]>(url)
      .pipe(
        tap(x => this.publisherList = x),
        catchError(this.handleError<Publisher[]>('getAllPublishers', []))
      );
  }

  getPublisher(id: number): Observable<Publisher> {
    const url = `${this.publisherUrl}/get-by-id/${id}`;
    return this.http.get<Publisher>(url)
      .pipe(
        catchError(this.handleError<Publisher>(`getPublisher id=${id}`))
      );
  }

  getPublishers(): Observable<Publisher[]> {
    if (this.publisherList.length === 0) {
      this.getAllPublishers().subscribe(
        res => {
          this.publisherList = res;
        }
      );
    }
    return of(this.publisherList);
  }

}

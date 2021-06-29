import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Genre} from '../../_model/genre';
import {catchError, tap} from 'rxjs/operators';
import {Author} from '../../_model/author';

@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  constructor(private http: HttpClient) { }

  private authorsList: Author[];
  private authorUrl = 'http://localhost:8081/author';

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

  getAllAuthors(): Observable<Author[]> {
    const url = `${this.authorUrl}/get-all`;
    return this.http.get<Author[]>(url)
      .pipe(
        tap(x => this.authorsList = x),
        catchError(this.handleError<Author[]>('getAllAuthors', []))
      );
  }

  getAuthor(id: number): Observable<Author> {
    const url = `${this.authorUrl}/get-by-id/${id}`;
    return this.http.get<Author>(url)
      .pipe(
        catchError(this.handleError<Author>(`getAuthor id=${id}`))
      );
  }

  getAuthors(): Observable<Author[]> {
    if (this.authorsList.length === 0) {
      this.getAllAuthors().subscribe(
        res => {
          this.authorsList = res;
        }
      );
    }
    return of(this.authorsList);
  }

}

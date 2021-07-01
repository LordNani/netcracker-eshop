import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError, map, tap} from 'rxjs/operators';
import {Observable, of} from 'rxjs';
import {Genre} from '../../_model/genre';
import {CoverType} from '../../_model/cover-type';


@Injectable({
  providedIn: 'root'
})
export class GenreService {

  constructor(private http: HttpClient) { }

  private genreUrl = 'http://localhost:8081/genre';

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

  getAllGenres(): Observable<Genre[]> {
    const url = `${this.genreUrl}/get-all`;
    return this.http.get<Genre[]>(url)
      .pipe(
        catchError(this.handleError<Genre[]>('getAllGenres', []))
      );
  }

  getGenre(id: number): Observable<Genre> {
    const url = `${this.genreUrl}/get-by-id/${id}`;
    return this.http.get<Genre>(url)
      .pipe(
        catchError(this.handleError<Genre>(`getGenre id=${id}`))
      );
  }

  getGenres(): Genre[] {
    if (localStorage.getItem('genres') === null) {
      this.getAllGenres().subscribe(
        res => {
          localStorage.setItem('genres', JSON.stringify(res));
        }
      );
    }
    return JSON.parse(localStorage.getItem('genres'));
  }

}



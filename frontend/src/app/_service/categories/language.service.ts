import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';
import {Language} from '../../_model/Language';
import {CoverType} from '../../_model/cover-type';

@Injectable({
  providedIn: 'root'
})
export class LanguageService {

  constructor(private http: HttpClient) { }

  private languageUrl = 'http://localhost:8081/language';

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

  getAllLanguages(): Observable<Language[]> {
    const url = `${this.languageUrl}/get-all`;
    return this.http.get<Language[]>(url)
      .pipe(
        catchError(this.handleError<Language[]>('getAllLanguages', []))
      );
  }

  getLanguage(id: number): Observable<Language> {
    const url = `${this.languageUrl}/get-by-id/${id}`;
    return this.http.get<Language>(url)
      .pipe(
        catchError(this.handleError<Language>(`getLanguage id=${id}`))
      );
  }

  getLanguages(): Language[] {
    if (localStorage.getItem('languages') === null) {
      this.getAllLanguages().subscribe(
        res => {
          localStorage.setItem('languages', JSON.stringify(res));
        }
      );
    }
    return JSON.parse(localStorage.getItem('languages'));
  }
}

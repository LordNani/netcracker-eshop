import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError} from "rxjs/operators";
import {Language} from "../../_model/Language";

@Injectable({
  providedIn: 'root'
})
export class LanguageService {

  private languageUrl = 'http://localhost:8081/language';

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient) { }

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
      )
  }
}

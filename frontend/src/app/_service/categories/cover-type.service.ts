import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';
import {CoverType} from '../../_model/cover-type';
import {Author} from '../../_model/author';

@Injectable({
  providedIn: 'root'
})
export class CoverTypeService {

  constructor(private http: HttpClient) { }

  private coverTypeUrl = 'http://localhost:8081/cover-type';


  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }private coverTypeList: CoverType[] = [];

  getAllCoverTypes(): Observable<CoverType[]> {
    const url = `${this.coverTypeUrl}/get-all`;
    return this.http.get<CoverType[]>(url)
      .pipe(
        tap(x => this.coverTypeList = x),
        catchError(this.handleError<CoverType[]>('getAllCoverTypes', []))
      );
  }

  getCoverType(id: number): Observable<CoverType> {
    const url = `${this.coverTypeUrl}/get-by-id/${id}`;
    return this.http.get<CoverType>(url)
      .pipe(
        catchError(this.handleError<CoverType>(`getCoverType id=${id}`))
      );
  }

  getCoverTypes(): Observable<CoverType[]> {
    if (this.coverTypeList.length === 0) {
      this.getAllCoverTypes().subscribe(
        res => {
          this.coverTypeList = res;
        }
      );
    }
    return of(this.coverTypeList);
  }
}

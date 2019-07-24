import { Injectable } from '@angular/core';
import { appConfig } from '../configs/app.config';
import { Observable, of} from 'rxjs';
import { Employee } from '../models/employee';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, tap } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private employeesUrl = `${appConfig.urlApi}/employee`;

  constructor(private http: HttpClient) { }

  /** GET employees from the server */
  getEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(this.employeesUrl);
      // .pipe(
      //   tap(_ => console.log(`Feched employees`)),
      //   catchError(this.handleError<Employee[]>('getEmployees', []))
      // );
  }

  /** POST: add a new employee to the server */
  registerEmployee(employee: Employee): Observable<Employee> {
    return this.http.post<Employee>(this.employeesUrl, employee, httpOptions);
  }

  loginEmployee(employee: Employee): Observable<Employee> {
      return this.http.post<Employee>(`${this.employeesUrl}/login`, employee, httpOptions);
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(error as T);
    };
  }
}

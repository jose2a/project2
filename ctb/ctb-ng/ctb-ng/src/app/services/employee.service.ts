import { Injectable } from '@angular/core';
import { appConfig } from '../configs/app.config';
import { Observable, of} from 'rxjs';
import { Employee } from '../models/employee';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { AuthService } from './auth.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: "root"
})
export class EmployeeService {
  private employeesUrl = `${appConfig.urlApi}/employee`;

  constructor(private http: HttpClient, private auth: AuthService) {}

  /** GET employees from the server */
  getEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(this.employeesUrl);
  }

  /** POST: add a new employee to the server */
  registerEmployee(employee: Employee): Observable<Employee> {
    return this.http.post<Employee>(this.employeesUrl, employee, httpOptions);
  }

  loginEmployee(employee: Employee): Observable<Employee> {
    return this.http
      .post<Employee>(`${this.employeesUrl}/login`, employee, httpOptions)
      .pipe(
        tap((emp: Employee) => {
          console.log(emp);

          this.auth.setLoggedEmployee(emp);
        })
      );
  }

  logoutEmployee(): Observable<Employee> {
    return this.http
      .get<Employee>(`${this.employeesUrl}/logout`, httpOptions)
      .pipe(
        tap(() => {
          console.log('Logout');

          this.auth.clearLoggedEmployee();
        })
      );
  }
}

import { Injectable } from '@angular/core';
import { appConfig } from '../configs/app.config';
import { Observable, of} from 'rxjs';
import { Employee } from '../models/employee';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';

const httpOptions = {
  headers: new HttpHeaders({ "Content-Type": "application/json" }),
  withCredentials: true
};

@Injectable({
  providedIn: "root"
})
export class EmployeeService {
  private employeesUrl = `${appConfig.urlApi}/employee`;

  constructor(
    private http: HttpClient,
    private auth: AuthService,
    private router: Router
  ) {}

  /** GET employees from the server */
  getEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(this.employeesUrl);
  }

  /** POST: add a new employee to the server */
  registerEmployee(employee: Employee): Observable<Employee> {
    return this.http.post<Employee>(this.employeesUrl, employee, httpOptions);
  }

  loginEmployee(employee: Employee): Observable<Employee> {
    const login = {
      username: employee.username,
      password: employee.password
    };

    return this.http
      .post<Employee>(`${this.employeesUrl}/login`, login, httpOptions)
      .pipe(
        tap((emp: Employee) => {
          localStorage.setItem("employee", JSON.stringify(emp));

          this.auth.setLoggedEmployee(emp);
        })
      );
  }

  logoutEmployee() {
    console.log("Logout");

    return this.http.get(`${this.employeesUrl}/logout`, httpOptions).pipe(
      tap(() => {
        console.log("Logout observable");

        localStorage.removeItem("employee");
        this.auth.clearLoggedEmployee();

        this.router.navigate(["/"]);
      })
    );
  }
}

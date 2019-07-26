import { Injectable } from '@angular/core';
import { Employee } from '../models/employee';
import { Subject, Observable, empty, BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loggedEmployee$ = new Subject<Employee>();

  //user related properties
  private UserRole = new BehaviorSubject<string>(localStorage.getItem('userRole'));

  constructor() { }

  getLoggedEmployee(): Observable<Employee> {
    return this.loggedEmployee$.asObservable();
  }

  setLoggedEmployee(employee: Employee): void {
    console.log(employee);
    this.loggedEmployee$.next(employee);
  }

  clearLoggedEmployee(): void {
    this.loggedEmployee$.next(null);
  }

  getEmployeeFromSession(): Employee {
    const emp = JSON.parse(localStorage.getItem('employee'));

    return emp;
  }

  get currentUserRole() {
    return this.UserRole.asObservable();
  }

}

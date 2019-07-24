import { Injectable } from '@angular/core';
import { Employee } from '../models/employee';
import { Subject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loggedEmployee$ = new Subject<Employee>();

  constructor() { }

  getLoggedEmployee(): Observable<Employee> {
    return this.loggedEmployee$.asObservable();
  }

  setLoggedEmployee(employee: Employee): void {
    this.loggedEmployee$.next(employee);
  }

  clearLoggedEmployee(): void {
    this.loggedEmployee$.next(null);
  }

}

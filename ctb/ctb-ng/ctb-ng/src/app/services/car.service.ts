import { Injectable } from '@angular/core';

import { HttpHeaders, HttpClient } from '@angular/common/http';
import { appConfig } from '../configs/app.config';
import { Observable } from 'rxjs';
import { Car } from '../models/car';

const httpOptions = {
  headers: new HttpHeaders({ "Content-Type": "application/json" }),
  withCredentials: true
};

@Injectable({
  providedIn: 'root'
})
export class CarService {

  private carUrl = `${appConfig.urlApi}/employee`;

  constructor(private http: HttpClient) { }

  /*GET car by employeeId*/
  getCarsByEmployeeId(employeeId: number): Observable <Car[]>{
    return this.http.get<Car[]>(`${this.carUrl}/${employeeId}/car`, httpOptions);
  }
}


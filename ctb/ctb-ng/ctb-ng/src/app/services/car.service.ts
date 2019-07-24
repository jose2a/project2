import { Injectable } from '@angular/core';
import { appConfig } from '../configs/app.config';
import { Observable, of } from 'rxjs';
import { Car } from '../models/car';
import { HttpClient, HttpHeaders } from '@angular/common/http';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
}; 

@Injectable({
  providedIn: 'root'
})
export class CarService {

  private carUrl = `${appConfig.urlApi}/employee`;

  constructor(private http: HttpClient) { }

  /*GET car by employeeId*/
  getCarsByEmployeeId(employeeId: number): Observable <Car[]>{
    return this.http.get<Ride[]>(`${this.carUrl}/${employeeId}/car`);
  }
}


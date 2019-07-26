import { Injectable } from '@angular/core';
import { appConfig } from '../configs/app.config';
import { Observable, of } from 'rxjs';
import { Ride } from '../models/ride';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, tap } from 'rxjs/operators';


const httpOptions = {
  headers: new HttpHeaders({ "Content-Type": "application/json" }),
  withCredentials: true
};

@Injectable({
  providedIn: "root"
})
export class RideService {
  private rideUrl = `${appConfig.urlApi}/ride`;

  constructor(private http: HttpClient) {}

  /*GET rides from server*/
  getRides(): Observable<Ride[]> {
    return this.http.get<Ride[]>(this.rideUrl, httpOptions);
  }

  getRidesByEmployee(employeeId: number): Observable<Ride[]> {
    return this.http.get<Ride[]>(`${this.rideUrl}/employee/${employeeId}`, httpOptions);
  }

  /*POST ride from server*/
  createRide(ride: Ride): Observable<Ride> {
    return this.http.post<Ride>(this.rideUrl, ride, httpOptions);
  }

  /*PUT ride --update*/
  updateRide(ride: Ride): Observable<Ride> {
    const url = `${this.rideUrl}/${ride.rideId}`;
    return this.http.put<Ride>(this.rideUrl, ride, httpOptions);
  }

  /*PUT ride --cancel*/
  softCancelRide(ride: Ride): Observable<Ride> {
    const url = `${this.rideUrl}/${ride.rideId}/cancel`;
    return this.http.put<Ride>(this.rideUrl, ride, httpOptions);
  }

  /*GET employee rides*/
  getAvailableRides(): Observable<Ride[]> {
    return this.http.get<Ride[]>(`${this.rideUrl}/available`, httpOptions);
  }

  /*GET ride with ID*/
  getRideById(rideId: number): Observable<Ride> {
    return this.http.get<Ride>(`${this.rideUrl}/${rideId}`, httpOptions);
  }

  /*POST message to passengers*/
  postMessage(message: any): Observable<void> {
    return this.http.post<void>(
      `${this.rideUrl}/message`,
      message,
      httpOptions
    );
  }
}
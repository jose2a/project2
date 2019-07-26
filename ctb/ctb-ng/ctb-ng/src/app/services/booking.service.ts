import { Injectable } from '@angular/core';
import { appConfig } from '../configs/app.config';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Booking } from '../models/booking';

const httpOptions = {
  headers: new HttpHeaders({ "Content-Type": "application/json" }),
  withCredentials: true
};

@Injectable({
  providedIn: "root"
})
export class BookingService {
  private bookingUrl = `${appConfig.urlApi}/booking`;

  constructor(private http: HttpClient) {}

  /*GET rides from server*/
  getBooking(): Observable<Booking[]> {
    return this.http.get<Booking[]>(`${this.bookingUrl}/booking`, httpOptions);
  }

  getBookingByEmployee(employeeId: number): Observable<Booking[]> {
    return this.http.get<Booking[]>(`${appConfig.urlApi}/employee/${employeeId}/booking`, httpOptions);
  }

  /*POST ride from server*/
  createBooking(booking: Booking): Observable<Booking> {
    console.log(this.bookingUrl);
    
    return this.http.post<Booking>(
      `${this.bookingUrl}`,
      booking,
      httpOptions
    );
  }
}

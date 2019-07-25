import { Injectable } from '@angular/core';
import { appConfig } from '../configs/app.config';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Booking } from '../models/booking';

const httpOptions = {
  headers: new HttpHeaders({ "Content-Type": "application/json" })
};

@Injectable({
  providedIn: "root"
})
export class BookingService {
  private bookingUrl = `${appConfig.urlApi}/booking`;

  constructor(private http: HttpClient) {}

  /*GET rides from server*/
  getBooking(): Observable<Booking[]> {
    return this.http.get<Booking[]>(`${this.bookingUrl}/booking`);
  }

  getBookingByEmployee(employeeId: number): Observable<Booking[]> {
    return this.http.get<Booking[]>(`${this.bookingUrl}/employee/${employeeId}`);
  }

  /*POST ride from server*/
  createBooking(booking: Booking): Observable<Booking> {
    return this.http.post<Booking>(
      `${this.bookingUrl}/booking`,
      booking,
      httpOptions
    );
  }
}

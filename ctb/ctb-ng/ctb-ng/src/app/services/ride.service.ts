import { Injectable } from '@angular/core';
import { appConfig } from '../configs/app.config';
import { Observable, of } from 'rxjx';
import { Ride } from '../models/ride';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RideService {

  private rideUrl = `${appConfig.urlApi}/ride`;

  constructor(private http: HttpClient) { }

  
}

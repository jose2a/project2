import { Injectable } from '@angular/core';
import { appConfig } from '../configs/app.config';
import { Observable, of } from 'rxjs';
import { Feedback } from '../models/feedback';
import { HttpClient, HttpHeaders } from '@angular/common/http';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {

  private feedbackUrl = `${appConfig.urlApi}/feedback`;

  constructor(private http: HttpClient) { }

  /*GET feedback type*/
  getFeedbackType(feedbackTypeId: number): Observable <Feedback> {
    return this.http.get<Feedback> (`{$this.feedbackUrl}/${feedbackTypeId}`);
  }
  /*GET all feedback*/
  getFeedback(): Observable <Feedback[]> {
    return this.http.get<Feedback[]>(this.feedbackUrl);
  }
}

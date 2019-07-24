import { Injectable } from '@angular/core';
import { appConfig } from '../configs/app.config';
import { Observable, of } from 'rxjs';
import { InfoReq } from '../models/infoReq';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Ride } from '../models/ride';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class InfoReqService {

  private infoReqUrl = `${appConfig.urlApi}/information`;

  constructor(private http: HttpClient) { }


   /*GET all information*/
   getInformation(): Observable <InfoReq[]> {
    return this.http.get<InfoReq[]>(this.infoReqUrl);
  }

   /*POST infoReq from server*/
   createInfoReq(infoReqId: InfoReq): Observable <InfoReq> {
    return this.http.post<InfoReq>(this.infoReqUrl, infoReqId, httpOptions)
  }

  /*PUT infoReq --update*/
  updateInfoReq(infoReq: InfoReq): Observable<InfoReq> {
    const url = `${this.infoReqUrl}/${infoReq.infoReqId}`;
    return this.http.put<InfoReq>(this.infoReqUrl, infoReq, httpOptions);
  }

  /*PUT infoReq -- confirm*/
  confirmRequestReceived(infoReqId: number){
    const url = `${this.infoReqUrl}/${infoReqId}/confirmed`;
    return this.http.put<InfoReq>(this.infoReqUrl, infoReq, httpOptions);
  }

  /*GET infoReq by employeeID*/
  getReqById(employeeId: number): Observable <InfoReq[]>{
    return this.http.get<InfoReq[]>(`${this.infoReqUrl}/employee/${employeeId}`);
  }

  /*GET infoReq by ID*/
  getInfoReqById(infoReqId : number): Observable <InfoReq> {
    return this.http.get<InfoReq>(`${this.infoReqUrl}/${infoReqId}`);
  }

}

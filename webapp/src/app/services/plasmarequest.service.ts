import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { PlasmaRequest } from './request';

@Injectable({
  providedIn: 'root'
})
export class PlasmarequestService {

  constructor(private http: HttpClient) { }

  addPlasmaRequest(plasmaRequest:PlasmaRequest):Observable<PlasmaRequest>{
    return this.http.post<PlasmaRequest>('http://localhost:8085/api/v1/plasmaRequest',plasmaRequest);
  }

  getDonar(){
    return this.http.get('http://localhost:8085/api/v1/donors');
  }

  
 
   

  
}

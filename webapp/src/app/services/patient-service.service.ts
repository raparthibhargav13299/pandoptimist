
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Patient } from './patient';

@Injectable({
  providedIn: 'root'
})
export class PatientServiceService {

  constructor(private http: HttpClient) { }

  addRequest(patient:Patient):Observable<Patient>{
    return this.http.post<Patient>('http://localhost:8085/api/v1/medicalRequest',patient);
  }
}

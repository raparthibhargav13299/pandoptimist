import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MedicalRequest } from '../Model/MedicalResquest';

@Injectable({
  providedIn: 'root'
})
export class SOSRequestService {

  constructor(private http: HttpClient) { }
  getRandomRequests(): Observable<MedicalRequest> {
    return this.http.get<MedicalRequest>(`http://localhost:9091/api/v1/resource/openRequest/random`);
  }
}

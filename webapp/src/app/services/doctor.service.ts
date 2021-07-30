import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';

import { Doctor } from '../Model/Doctor';
import { DoctorRedis } from '../Model/DoctorRedis';
import { HealthTips } from './../Model/HealthTips';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  constructor(private http: HttpClient) { }
  getDoctors(): Observable<Array<Doctor>> {
    return this.http.get<Array<Doctor>>(`http://localhost:8082/api/v1/doctors`);
  }

  addDoctor(doctor: Doctor): Observable<Doctor> {
    return this.http.post<Doctor>(`http://localhost:8082/api/v1/doctor`, doctor);
  }

  getHealthTips(): Observable<Array<HealthTips>> {
    return this.http.get<Array<HealthTips>>(`http://localhost:8082/api/v1/allhealthTips`);
  }

  addHealthTips(healthTip: HealthTips): Observable<HealthTips> {
    return this.http.post<HealthTips>(`http://localhost:8082/api/v1/healthTip`, healthTip);
  }


  
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DoctorRedis } from '../Model/DoctorRedis';

@Injectable({
  providedIn: 'root'
})
export class ConsultantServiceService {

  constructor(private http:HttpClient) { }

  getAllDoctorsOnline(): Observable<Array<DoctorRedis>>{
   return this.http.get<Array<DoctorRedis>>('http://localhost:8082/api/v1/getdoctorsOnline');
  }

  doctorEngaged:DoctorRedis=new DoctorRedis();





}

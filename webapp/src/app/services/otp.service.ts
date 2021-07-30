import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';

import { Mail } from '../Model/Mail';
import { User } from '../Model/OTP';


@Injectable({
  providedIn: 'root'
})
export class OtpService {

  constructor(private http: HttpClient) { }
  
  sendMailForOtp(email:Mail): Observable<Mail>{
    console.log(email)
    return this.http.post<Mail>('http://localhost:8081/api/v1/user', email);
  }


  OtpVerification(otpnum:Number,otpmail:String):Observable<String>{
    var reqHeader = new HttpHeaders({ 'Content-Type': 'application/json','No-Auth':'True' });
    return this.http.get(`http://localhost:8081/api/v1/validate/otp/${otpnum}/mail/${otpmail}`, {headers:reqHeader, responseType:'text'});
   
  }

  }


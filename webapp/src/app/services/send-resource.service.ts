import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EmailQueue } from '../Model/EmailQueue';
import { MedicalRequest } from '../Model/MedicalResquest';

@Injectable({
  providedIn: 'root'
})
export class SendResourceService {

  constructor(private http: HttpClient) { }

  addResource(emailQueue:EmailQueue,id:Number,body:String,email:String): Observable<EmailQueue> {
    
    return this.http.post<EmailQueue>(`http://localhost:9091/api/v1/resource/close/${id}/${email}?body=${body}`,emailQueue);
  }

  passRequest(id:Number,medical:MedicalRequest): Observable<MedicalRequest> {
    
    return this.http.put<MedicalRequest>(`http://localhost:9091/api/v1/resource/pass/${id}`,medical);
  }
}

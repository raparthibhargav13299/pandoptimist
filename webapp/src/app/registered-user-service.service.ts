import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RegisteredUser } from './registered-user';

@Injectable({
  providedIn: 'root'
})
export class RegisteredUserServiceService {

  private baseURL="http://localhost:8080/pand-optimist/v1.0.0/registerusers";
  
  constructor(private http: HttpClient) { }

  registerUser(regUser: RegisteredUser): Observable<RegisteredUser>{
    return this.http.post<RegisteredUser>("http://localhost:8080/pand-optimist/v1.0.0/registerusers", regUser);
  }
}

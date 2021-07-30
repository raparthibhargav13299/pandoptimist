import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RegisteredUser } from '../registered-user';


@Injectable({
  providedIn: 'root'
})
export class RegisteruserService {

  constructor(private http: HttpClient) { }


  registerUser(regUser: RegisteredUser): Observable<Object> {
    return this.http.post<RegisteredUser>("http://localhost:8080/api/v11/register", regUser);
  }
}

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RegisteredUser } from '../registered-user';
import { Observable, throwError } from 'rxjs';
import { DoctorRedis } from '../Model/DoctorRedis';


@Injectable({
  providedIn: 'root'
})
export class LoginuserService {

  loggedinuser: RegisteredUser = new RegisteredUser();
  constructor(private http: HttpClient) { }

  //for login user
  setToken(token) {
    localStorage.setItem("token", token);
    return true;
  }
  //to check that user is logged in
  isLoggedin() {

    let token = localStorage.getItem("token");
    if (token == null || token == '' || token == undefined) {
      return false;
    } else {
      return true;
    }
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    localStorage.removeItem('email');
    return true;
  }
  //get token
  getToken() {
    return localStorage.getItem("token");
  }


  //calling server to generate token
  public generateJwtToken(credentials: RegisteredUser): Observable<any> {
    return this.http.post("http://localhost:8080/api/v11/login", credentials, { observe: 'response' });
  }


  public roleUpdate(userWithRole: RegisteredUser): Observable<any> {
    let tokenStr = 'Bearer ' + this.getToken();
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    console.log('----------------');
    return this.http.post("http://localhost:8080/api/v11/updaterole", userWithRole);
  }

  public updateStatusOnline(doctorOnline:DoctorRedis): Observable<any>{
    return this.http.post("http://localhost:8082/api/v1/setstatusOnline",doctorOnline);
  }

  public setStatusBusy(doctorOnline:DoctorRedis): Observable<any>{
    return this.http.post("http://localhost:8082/api/v1/updatestatusBusy",doctorOnline);
  }







}

import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs/internal/Observable';
import { Volunteer } from '../Model/volunteer';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class VolunteerService {

  private apiServiceUrl=environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getVolunteers():Observable<Volunteer[]>{
    return this.http.get<Array<Volunteer>>(`http://localhost:8084/api/v1/volunteers`);
    }

    public addVolunteer(volunteer:Volunteer): Observable<Volunteer>{
      return this.http.post<Volunteer>(`http://localhost:8084/api/v1/volunteer`,volunteer);
    }

}

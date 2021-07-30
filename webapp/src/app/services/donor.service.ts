import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs/internal/Observable';
import { environment } from '../../environments/environment';
import { Donor } from '../Model/donor';

@Injectable({
  providedIn: 'root'
})
export class DonorService {


  private apiServiceUrl=environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getDonors():Observable<Donor[]>{
    return this.http.get<Array<Donor>>(`http://localhost:8083/api/v1/donors`);
    }

    public addDonor(donor:Donor): Observable<Donor>{
      return this.http.post<Donor>(`http://localhost:8083/api/v1/donor`,donor);
    }
  }

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Bed } from '../Model/Bed';
@Injectable({
  providedIn: 'root'
})
export class BedResourceService {

  constructor(private http: HttpClient) { }

  addBed(bed:Bed): Observable<Bed> {
    return this.http.post<Bed>(`http://localhost:9091/api/v1/resource/bed`, bed);
  }


  getBeds(): Observable<Array<Bed>> {
    return this.http.get<Array<Bed>>(`http://localhost:9091/api/v1/resource/beds`);
  }
}

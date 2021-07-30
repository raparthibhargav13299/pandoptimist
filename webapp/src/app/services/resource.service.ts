import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Resource } from '../Model/Resource';

@Injectable({
  providedIn: 'root'
})
export class ResourceService {

  constructor(private http: HttpClient) { }

  addResource(resource:Resource): Observable<Resource> {
    return this.http.post<Resource>(`http://localhost:9091/api/v1/resource/addResource`, resource);
  }


  getResources(): Observable<Array<Resource>> {
    return this.http.get<Array<Resource>>(`http://localhost:9091/api/v1/resource/allResources`);
  }

  searchResource(resourceName:String,cityName:String): Observable<Array<Resource>> {
    return this.http.get<Array<Resource>>(`http://localhost:9091/api/v1/resource/search/${resourceName}/${cityName}`)
  }
}

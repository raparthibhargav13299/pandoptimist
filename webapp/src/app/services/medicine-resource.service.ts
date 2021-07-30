import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Medicine } from '../Model/Medicine';

@Injectable({
  providedIn: 'root'
})
export class MedicineResourceService {

  constructor(private http: HttpClient) { }

  addMedicines(medicine: Medicine): Observable<Medicine> {
    console.log("aaaaaa"+medicine.isVerified)
    return this.http.post<Medicine>(`http://localhost:9091/api/v1/resource/medicine`, medicine);
  }


  getMedicines(): Observable<Array<Medicine>> {
    return this.http.get<Array<Medicine>>(`http://localhost:9091/api/v1/resource/medicines`);
  }
}

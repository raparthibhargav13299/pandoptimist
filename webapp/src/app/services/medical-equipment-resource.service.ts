import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MedicalEquipment } from '../Model/MedicalEquipment';

@Injectable({
  providedIn: 'root'
})
export class MedicalEquipmentResourceService {

  constructor(private http: HttpClient) { }

  addEquipments(medicalEquipment : MedicalEquipment): Observable<MedicalEquipment> {
    return this.http.post<MedicalEquipment>(`http://localhost:9091/api/v1/resource/medicalEquipment`, medicalEquipment);
  }


  getEquipments(): Observable<Array<MedicalEquipment>> {
    return this.http.get<Array<MedicalEquipment>>(`http://localhost:9091/api/v1/resource/medicalEquipments`);
  }
}

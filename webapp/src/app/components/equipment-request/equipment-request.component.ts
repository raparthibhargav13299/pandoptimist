import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MedicalEquipment } from 'src/app/Model/MedicalEquipment';
import { MedicalEquipmentResourceService } from 'src/app/services/medical-equipment-resource.service';
import { ResourceService } from 'src/app/services/resource.service';
interface type {
  medicalType: string;
 
}

interface AllEquipmentType {
  typeOfMedicalEquipment: string;
  
}
@Component({
  selector: 'app-equipment-request',
  templateUrl: './equipment-request.component.html',
  styleUrls: ['./equipment-request.component.css']
})
export class EquipmentRequestComponent implements OnInit {
  gettype: type[] = [
    {medicalType: 'Equipments'}
   
  ];
  EquipmentsArray:Array<MedicalEquipment>=[];
  allEquipmentTypes: AllEquipmentType[] = [
    {typeOfMedicalEquipment: 'Diagnostic equipment'},
    {typeOfMedicalEquipment: 'Therapeutic equipment'},
    {typeOfMedicalEquipment: 'Surgical instruments'},
    {typeOfMedicalEquipment: 'Durable medical equipment'},
    {typeOfMedicalEquipment: 'Biomedical equipment'},
    {typeOfMedicalEquipment: 'Oxygen Cylinders'},
   
  ];

  constructor(private http: HttpClient, private service:ResourceService) { }

  ngOnInit(): void {
  }

  EquipmentResourceForm = new FormGroup({
    createdBy:new FormControl(null,[Validators.required, Validators.minLength(4),Validators.pattern('^[A-Za-z\s]{1,}[\.]{0,1}[A-Za-z\s]{0,}$')]),
    type:new FormControl('',Validators.required),
    typeofEquipment:new FormControl('',Validators.required),
    medicalEquipmentName:new FormControl(null,[Validators.required, Validators.minLength(4),Validators.pattern('^[A-Za-z\s]{1,}[\.]{0,1}[A-Za-z\s]{0,}$')]),
    city:new FormControl(null,[Validators.required, Validators.minLength(4),Validators.pattern('^[A-Za-z\s]{1,}[\.]{0,1}[A-Za-z\s]{0,}$')]),
    avalabilityPlace:new FormControl(null,[Validators.required, Validators.minLength(4),Validators.pattern('^[A-Za-z\s]{1,}[\.]{0,1}[A-Za-z\s]{0,}$')]),
    address:new FormControl(null,[Validators.required, Validators.minLength(4),Validators.pattern('^[A-Za-z\s]{1,}[\.]{0,1}[A-Za-z\s]{0,}$')]),
    contactPersonName:new FormControl(null,[Validators.required, Validators.minLength(4),Validators.pattern('^[A-Za-z\s]{1,}[\.]{0,1}[A-Za-z\s]{0,}$')]),
    contactMobileNumber: new FormControl('',[Validators.required,
      Validators.pattern('(0/91)?[7-9][0-9]{9}')
    ]),
    isVerified:new FormControl(''),
    verifiedBy:new FormControl(''),

  });
 

  get createdBy() {
    return this.EquipmentResourceForm.get('createdBy');
  }

  get type() {
    return this.EquipmentResourceForm.get('type');
  }
  get typeofEquipment() {
    return this.EquipmentResourceForm.get('typeofEquipment');
  }
  get medicalEquipmentName() {
    return this.EquipmentResourceForm.get('medicalEquipmentName');
  }
  get city() {
    return this.EquipmentResourceForm.get('city');
  }
  get avalabilityPlace() {
    return this.EquipmentResourceForm.get('avalabilityPlace');
  }
  get address() {
    return this.EquipmentResourceForm.get('address');
  }
  get contactPersonName() {
    return this.EquipmentResourceForm.get('contactPersonName');
  }
  get contactMobileNumber() {
    return this.EquipmentResourceForm.get('contactMobileNumber');
  }
  get isVerified() {
    return this.EquipmentResourceForm.get('isVerified');
  }
  get verifiedBy() {
    return this.EquipmentResourceForm.get('verifiedBy');
  }


  registerEquipments(){
    console.log(this.EquipmentResourceForm.value)
    this.service.addResource(this.EquipmentResourceForm.value).subscribe((data)=>{
        this.EquipmentsArray.push(data)
        alert("Equipments successfully added")
    },error=>{
      console.log("Error in Equipment service")
    })
  }

  getEquipments(){
    console.log(this.EquipmentResourceForm.value)
    this.service.getResources().subscribe((data)=>{
    this.EquipmentsArray=data;
    },(error)=>{
      console.log("Error in Bed service")
    })
  }
}

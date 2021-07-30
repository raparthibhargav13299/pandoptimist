import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Medicine } from 'src/app/Model/Medicine';
import { MedicineResourceService } from 'src/app/services/medicine-resource.service';
import { ResourceService } from 'src/app/services/resource.service';
interface type {
  medicineType: string;
 
}
@Component({
  selector: 'app-medicine-request',
  templateUrl: './medicine-request.component.html',
  styleUrls: ['./medicine-request.component.css']
})
export class MedicineRequestComponent implements OnInit {
  gettype: type[] = [
    {medicineType: 'Medicine'}
   
  ];
  MedicineArray:Array<Medicine>=[];
  constructor(private http: HttpClient, private service:ResourceService) { }

  ngOnInit(): void {
  }

  MedicineResourceForm = new FormGroup({
    createdBy: new FormControl(null,[Validators.required, Validators.minLength(4),Validators.pattern('^[A-Za-z\s]{1,}[\.]{0,1}[A-Za-z\s]{0,}$')]),
    type:new FormControl('',Validators.required),
    medicineName:new FormControl(null,[Validators.required, Validators.minLength(4),Validators.pattern('^[A-Za-z\s]{1,}[\.]{0,1}[A-Za-z\s]{0,}$')]),
    city: new FormControl(null,[Validators.required, Validators.minLength(4),Validators.pattern('^[A-Za-z\s]{1,}[\.]{0,1}[A-Za-z\s]{0,}$')]),
    avalabilityPlace: new FormControl(null,[Validators.required, Validators.minLength(4),Validators.pattern('^[A-Za-z\s]{1,}[\.]{0,1}[A-Za-z\s]{0,}$')]),
    contactPersonName: new FormControl(null,[Validators.required, Validators.minLength(4),Validators.pattern('^[A-Za-z\s]{1,}[\.]{0,1}[A-Za-z\s]{0,}$')]),
    contactMobileNumber: new FormControl('',[Validators.required,
      Validators.pattern('(0/91)?[7-9][0-9]{9}')
    ]),
    isVerified:new FormControl('' ),
    verifiedBy:new FormControl(''),

  });


  
  get createdBy() {
    return this.MedicineResourceForm.get('createdBy');
  }

  get type() {
    return this.MedicineResourceForm.get('type');
  }
  get medicineName() {
    return this.MedicineResourceForm.get('medicineName');
  }
  get city() {
    return this.MedicineResourceForm.get('city');
  }
  get avalabilityPlace() {
    return this.MedicineResourceForm.get('avalabilityPlace');
  }
  get address() {
    return this.MedicineResourceForm.get('address');
  }
  get contactPersonName() {
    return this.MedicineResourceForm.get('contactPersonName');
  }
  get contactMobileNumber() {
    return this.MedicineResourceForm.get('contactMobileNumber');
  }
  get isVerified() {
    return this.MedicineResourceForm.get('isVerified');
  }
  get verifiedBy() {
    return this.MedicineResourceForm.get('verifiedBy');
  }


  registerMedicine(){
    console.log(this.MedicineResourceForm.value)
    console.log("aaaaaa"+this.MedicineResourceForm.get('isVerified').value)
    this.service.addResource(this.MedicineResourceForm.value).subscribe((data)=>{
        this.MedicineArray.push(data)
        alert("Medicine successfully added")
    },error=>{
      console.log("Error in Medicine service")
    })
  }

  getMedicines(){
    console.log(this.MedicineResourceForm.value)
    
    this.service.getResources().subscribe((data)=>{
    this.MedicineArray=data;
    },(error)=>{
      console.log("Error in Medicne service")
    })
  }
}

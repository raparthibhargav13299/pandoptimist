import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Bed } from 'src/app/Model/Bed';
import { Resource } from 'src/app/Model/Resource';
import { BedResourceService } from 'src/app/services/bed-resource.service';
import { ResourceService } from 'src/app/services/resource.service';

interface BedType {
  bedType: string;
 
}

interface AllBedType {
  typeOfBeds: string;
  
}

@Component({
  selector: 'app-bed-request',
  templateUrl: './bed-request.component.html',
  styleUrls: ['./bed-request.component.css']
})
export class BedRequestComponent implements OnInit {
  BedArray:Array<Resource>=[];
  resource:Resource=new Resource();
// ResourceArray:Array<Resource>=[];
  allbedTypes: AllBedType[] = [
    {typeOfBeds: 'Normal Bed'},
    {typeOfBeds: 'Bed with ventilator'},
    {typeOfBeds: 'Bed with oxygen'},
   
  ];

  bedTypes: BedType[] = [
    {bedType: 'Bed'}
   
  ];


  constructor(private http: HttpClient, private service:ResourceService) { }

  ngOnInit(): void {
  }

  BedResourceForm = new FormGroup({
    createdBy:new FormControl(null,[Validators.required, Validators.minLength(4),Validators.pattern('^[A-Za-z\s]{1,}[\.]{0,1}[A-Za-z\s]{0,}$')]),
    type:new FormControl('',Validators.required),
    bedType:new FormControl('',Validators.required),
    city:new FormControl('',Validators.required),
    avalabilityPlace:new FormControl('',Validators.required),
    address:new FormControl('',Validators.required),
    contactPersonName:new FormControl('',[Validators.required, Validators.minLength(4),Validators.pattern('^[A-Za-z\s]{1,}[\.]{0,1}[A-Za-z\s]{0,}$')]),
    contactMobileNumber:new FormControl('',[Validators.required,
      Validators.pattern('(0/91)?[7-9][0-9]{9}')]),
    isVerified:new FormControl('',Validators.required),
    verifiedBy:new FormControl('',[Validators.required, Validators.minLength(4),Validators.pattern('^[A-Za-z\s]{1,}[\.]{0,1}[A-Za-z\s]{0,}$')]),

  });



 
  get createdBy() {
    return this.BedResourceForm.get('createdBy');
  }

  get type() {
    return this.BedResourceForm.get('type');
  }
  get bedType() {
    return this.BedResourceForm.get('bedType');
  }
  get city() {
    return this.BedResourceForm.get('city');
  }
  get avalabilityPlace() {
    return this.BedResourceForm.get('avalabilityPlace');
  }
  get address() {
    return this.BedResourceForm.get('address');
  }
  get contactPersonName() {
    return this.BedResourceForm.get('contactPersonName');
  }
  get contactMobileNumber() {
    return this.BedResourceForm.get('contactMobileNumber');
  }
  get isVerified() {
    return this.BedResourceForm.get('isVerified');
  }
  get verifiedBy() {
    return this.BedResourceForm.get('verifiedBy');
  }

  registerBed(){
    console.log(this.BedResourceForm.value)
    this.service.addResource(this.BedResourceForm.value).subscribe((data)=>{
        this.BedArray.push(data)
        this.resource= new Resource();
        alert("Beds successfully added")
        // this.BedResourceForm.
    },error=>{
      console.log("Error in Bed service")
    })

  }

  getBeds(){
    console.log(this.BedResourceForm.value)
    this.service.getResources().subscribe((data)=>{
    this.BedArray=data;
    },(error)=>{
      console.log("Error in Bed service")
    })
  }
}

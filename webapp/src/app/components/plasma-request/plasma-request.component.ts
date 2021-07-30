import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { PlasmarequestService } from 'src/app/services/plasmarequest.service';
import { PlasmaRequest } from 'src/app/services/request';

interface BloodGroup {
  bloodType: string;
  bloodName: string;
}

@Component({
  selector: 'app-plasma-request',
  templateUrl: './plasma-request.component.html',
  styleUrls: ['./plasma-request.component.css']
})
export class PlasmaRequestComponent implements OnInit {

  bloodGroups: BloodGroup[] = [
    { bloodType: 'A+', bloodName: 'A+' },
    { bloodType: 'A-', bloodName: 'A-' },
    { bloodType: 'B+', bloodName: 'B+' },
    { bloodType: 'B-', bloodName: 'B-' },
    { bloodType: 'AB+', bloodName: 'AB+' },
    { bloodType: 'AB-', bloodName: 'AB-' },
    { bloodType: 'O-', bloodName: 'O-' },
    { bloodType: 'O+', bloodName: 'O+' },
  ];

  selectedFile: any;
  message: string;
  errors = errorMessages;
  condition: boolean = false;
  donars: any;
  constructor(private service: PlasmarequestService, private fb: FormBuilder, private http: HttpClient) { }
  public DonarArray: PlasmaRequest[][];


  public donarForm = this.fb.group({
    name: ['', [Validators.required, Validators.minLength(4), Validators.pattern('^[A-Za-z\s]{1,}[\.]{0,1}[A-Za-z\s]{0,}$')]],
    age: ['', [Validators.required, Validators.pattern('[0-9][0-9]')]],
    bloodGroup: ['', [Validators.required]],
    hospitalName: ['', [Validators.required, Validators.minLength(3)]],
    hospitalAddress: ['', [Validators.required,Validators.minLength(4)]],
    uploadPrescription: ['']

  })
  ngOnInit(): void {
  }
  requestSubmit() {
    if (!this.donarForm.valid) {
      alert("Please fill the form")
    }
    else {
      this.condition = true;
      this.service.addPlasmaRequest(this.donarForm.value).subscribe(data => {
        console.log(data);


      })
    }
  }


  getDonars() { 
    this.service.getDonar().subscribe(data => {
      this.donars = data;
    })
  }


  public onFileChanged(event) {
    this.selectedFile = event.target.files[0];
  }
  onUpload() {
    console.log(this.selectedFile);
    const file = new FormData();
    console.log(file + "..............")
    file.append('file', this.selectedFile, this.selectedFile.name);
    this.http.post('http://localhost:8085/uploadFile', file, { observe: 'response' })
      .subscribe((response) => {
        if (response.status === 200) {
          alert("Image uploaded successfully");
          this.message = 'Image uploaded successfully';
        } else {
          this.message = 'Image not uploaded successfully';
        }
      }
      );
  }

  get name() { return this.donarForm.get('name') }
  get age() { return this.donarForm.get('age') }
  get bloodGroup() { return this.donarForm.get('bloodGroup') }
  get hospitalName() { return this.donarForm.get('hospitalName') }
  get hospitalAddress() { return this.donarForm.get('hospitalAddress') }



   


}
export const errorMessages: { [key: string]: string } = {
  name: 'Full name must be between 1 and 20 characters',
  age: 'Please Enter Correct Age',
  phoneNumber: 'Please provide correct mobile number (length should be 10)',
  hospitalName: 'Please enter Correct Hospital Name',
  hospitalAddress: 'Please enter Correct Hospital Address'
};


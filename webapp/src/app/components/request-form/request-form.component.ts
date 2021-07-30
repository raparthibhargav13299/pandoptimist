import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Patient } from 'src/app/services/patient';
import { PatientServiceService } from 'src/app/services/patient-service.service';

@Component({
  selector: 'app-request-form',
  templateUrl: './request-form.component.html',
  styleUrls: ['./request-form.component.css']
})
export class RequestFormComponent implements OnInit {
    condition: boolean = false;
  selectedFile: any;
  message: string;
  errors = errorMessages;
  public RequestList!: FormArray;


  get contactFormGroup() {  
    return this.patientForm.get('requirement') as FormArray;
  }


  constructor(private service: PatientServiceService, private fb: FormBuilder, private http: HttpClient) { }
  public patientArray: Patient[][];
  public patientForm = this.fb.group({
    name: ['', [Validators.required, Validators.minLength(4), Validators.pattern('^[A-Za-z\s]{1,}[\.]{0,1}[A-Za-z\s]{0,}$')]],
    gender: ['', [Validators.required]],
    phoneNumber: ['', [Validators.required,
    Validators.pattern('(0/91)?[7-9][0-9]{9}')]],
    email: ['', [Validators.required, Validators.email, Validators.pattern('^[a-z0-9](\.?[a-z0-9]){5,}@gmail\.com$')]],
    hospitalized: ['', [Validators.required]],
    city: ['', [Validators.required, Validators.pattern('[a-zA-Z]{1,}')]],
    requirement: this.fb.array([this.createContact()]),
    uploadPrescription: [''],

  });

  ngOnInit(): void {
    this.RequestList = this.patientForm.get('requirement') as FormArray;
  }

  createContact(): FormGroup {  
    return this.fb.group({

      requirementName: [null, Validators.compose([Validators.required])],
      quantity: [null, Validators.compose([Validators.required])],
      unitOfMeasure:[null,Validators.required]
    });
  }


  addRequest() {
    this.RequestList.push(this.createContact());
  }


  removeRequest(index: number) {

    this.RequestList.removeAt(index);
  }


  getContactsFormGroup(index: any): FormGroup {

    const formGroup = this.RequestList.controls[index] as FormGroup;
    return formGroup;
  }


  onSubmit(){
    if (!this.patientForm.valid) {
      alert("Please fill the form")
    }
    else {
      this.condition = true;
      this.service.addRequest(this.patientForm.value).subscribe(data => {
        console.log(data);
      })

    }
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

  get name() { return this.patientForm.get('name') }
  get gender() { return this.patientForm.get('gender') }
  get phoneNumber() { return this.patientForm.get('phoneNumber') }
  phoneNumberpattern = "^((\\+91-?)|0)?[0-9]{10}$";
  get email() { return this.patientForm.get('email') }
  emailPattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";
  get hospitalized() { return this.patientForm.get('hospitalized') }
  get city() { return this.patientForm.get('city') }
  get uploadPrescription() { return this.patientForm.get('uploadPrescription') }


}
export const errorMessages: { [key: string]: string } = {
  name: 'Full name must be between 1 and 20 characters',
  email: 'Email must be a valid email address (username@domain)',
  phoneNumber: 'Please provide correct mobile number (length should be 10)',
  requirement: 'Please Enter Character',
  city: 'PLease Enter Valid City'
};


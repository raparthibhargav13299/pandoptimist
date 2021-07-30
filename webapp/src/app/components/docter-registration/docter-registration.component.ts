import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Doctor } from 'src/app/Model/Doctor';
import { Mail } from 'src/app/Model/Mail';
import { DoctorService } from 'src/app/services/doctor.service';
import { OtpService } from 'src/app/services/otp.service';
import { RouterService } from 'src/app/services/router.service';

@Component({
  selector: 'app-docter-registration',
  templateUrl: './docter-registration.component.html',
  styleUrls: ['./docter-registration.component.css']
})
export class DocterRegistrationComponent implements OnInit {

  constructor(private http: HttpClient, private service:DoctorService,private otpService: OtpService,private routerService:RouterService ) { }

  errors = errorMessages;
  selectedFile: File;
  optmessage:String;
  otpNumber:Number;
  myemail:Mail = new Mail();
  doctor:Doctor=new Doctor();
  otpverified:String;
    retrievedImage: any;
    base64Data: any;
  
    retrieveResonse: any;
  
    message: string;
  
    imageName: any;

  

  doctorArray:Array<Doctor>=[];
  ngOnInit(): void {
  }

  doctrorRegisterForm = new FormGroup({
    name: new FormControl(null,[Validators.required, Validators.minLength(4),Validators.pattern('^[A-Za-z\s]{1,}[\.]{0,1}[A-Za-z\s]{0,}$')]),
    medicalRegistrationNumber: new FormControl('',[Validators.required,Validators.minLength(5)]),
    mobileNumber:new FormControl('',[Validators.required,
      // Validators.pattern('^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$')
      Validators.pattern('(0/91)?[7-9][0-9]{9}')
    ]),
    email: new FormControl('',[Validators.required,Validators.email,Validators.pattern('^[a-z0-9](\.?[a-z0-9]){5,}@gmail\.com$')]),
    image: new FormControl('',Validators.required),
    otp:new FormControl('',Validators.required)
  })

get name() {
    return this.doctrorRegisterForm.get('name');
  }

  get medicalRegistrationNumber() {
    return this.doctrorRegisterForm.get('medicalRegistrationNumber');
  }
  get mobileNumber() {
    return this.doctrorRegisterForm.get('mobileNumber');
  }
  get email() {
    return this.doctrorRegisterForm.get('email');
  }  

  getDoctor(){
    console.log(this.doctrorRegisterForm.value)
    this.service.getDoctors().subscribe((data)=>{
    this.doctorArray=data;
    },(error)=>{
      console.log("Error!!!!!!")
    })
  }

  register(){
    console.log(this.doctrorRegisterForm.value)
    if(!this.doctrorRegisterForm.valid){
        alert("please fill the form")
    }
    
    else{
console.log()
      if(this.otpverified!=="VALID"){
        alert("Please verify your OTP");
      }
      else{

      
      this.service.addDoctor(this.doctrorRegisterForm.value).subscribe((data)=>{
      this.doctorArray.push(data);
      this.routerService.routeToDashboard();
      },(error)=>{
        console.log("Error!!!!!!")
      })
    }
  }
}

  getOtp(){
    console.log(this.myemail.mail);
    this.otpService.sendMailForOtp(this.myemail).subscribe((data)=>{
        this.optmessage="Success";
        console.log(this.optmessage);
    })
  }

  verifyOtp(){
    // console.log(this.otpNumber);
    this.otpService.OtpVerification(this.otpNumber,this.myemail.mail).subscribe((data)=>{
      if(data=="OTP IS VALID"){
        this.otpverified="VALID";
        alert("Otp is valid")
        
      }
      else{
        alert("Otp is Invalid")
      }

    },(error)=>{
      console.log(error)
    })
  }


  public onFileChanged(event) {

        this.selectedFile = event.target.files[0];

      }

      onUpload() {
        
            console.log(this.selectedFile);
        
            //FormData API provides methods and properties to allow us easily prepare form data to be sent with POST HTTP requests.
        
            const file = new FormData();
            console.log(file+"..............")
        
            file.append('file', this.selectedFile, this.selectedFile.name);
        
            //Make a call to the Spring Boot Application to save the image
        
            this.http.post('http://localhost:8082/upload-file', file, { observe: 'response' })
        
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
          

}
export const errorMessages: { [key: string]: string } = {
  name: 'Full name must be between 1 and 128 characters',
  email: 'Email must be a valid email address (username@domain)',
  mobileNumber: 'Please provide correct mobile number (length should be 10)',
  medicalRegistrationNumber:''
};

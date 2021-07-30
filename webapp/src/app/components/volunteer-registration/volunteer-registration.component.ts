import { Component, OnInit } from '@angular/core';
import { Volunteer } from '../../Model/volunteer';
import { HttpErrorResponse, HttpClient } from '@angular/common/http';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { VolunteerService } from 'src/app/services/volunteer.service';
import { RouterService } from 'src/app/services/router.service';
import { Mail } from 'src/app/Model/Mail';
import { OtpService } from 'src/app/services/otp.service';
import { LoginuserService } from 'src/app/services/loginuser.service';


@Component({
  selector: 'app-volunteer-registration',
  templateUrl: './volunteer-registration.component.html',
  styleUrls: ['./volunteer-registration.component.css']
})
export class VolunteerRegistrationComponent implements OnInit {

  errors = errorMessages;
  showMsg: boolean = false;
  otpNumber:Number;
  myemail:Mail = new Mail();
  selectedFile: File;
  message: string;
  retrievedImage: any;
  otpverified:String;
    base64Data: any;
    optmessage:String;
    retrieveResonse: any;
  
   
    imageName: any;


  public volunteers: Volunteer[];
  constructor(private http:HttpClient ,private volunteerService :VolunteerService, private routerService: RouterService,private otpService:OtpService, 
    private loginservice:LoginuserService){}
  volunteerArray:Array<Volunteer>=[]; 

  ngOnInit() {
  }

  volunteerform= new FormGroup({
    volunteer_name: new FormControl(null,[Validators.required, Validators.minLength(4),Validators.pattern('^[a-zA-Z]+( [a-zA-Z]+)+$')]),
    volunteer_mobileNumber: new FormControl(null,[Validators.required,Validators.maxLength(10), Validators.pattern('(0/91)?[7-9][0-9]{9}')
    ]),
    volunteer_email :new FormControl(null,[Validators.required, Validators.pattern('^[a-z0-9](\.?[a-z0-9]){5,}@gmail\.com$')]),
    otp : new FormControl(null, [Validators.required,Validators.maxLength(6), Validators.pattern("^[0-9]*$")]),
    filepath: new FormControl('',Validators.required)
  });

  
  get volunteer_name() {
    return this.volunteerform.get('volunteer_name');
  }
  get volunteer_email() {
    return this.volunteerform.get('volunteer_email');
  }
  get volunteer_mobileNumber() {
    return this.volunteerform.get('volunteer_mobileNumber');
  }
  get otp() {
    return this.volunteerform.get('otp');
  }

  public getVolunteersAvailable(){

    this.volunteerService.getVolunteers().subscribe(
      (response:Volunteer[])=>{
        this.volunteers=response;
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
    );
  }
  
  VolunteerRegister(){
    console.log("asdasdasdasd");
    if(!this.volunteerform.valid){
      alert("please fill the form")
  }
  else{
    console.log()
          if(this.otpverified!=="VALID"){
            alert("Please verify your OTP");
          }
          else{
  
    this.volunteerService.addVolunteer(this.volunteerform.value).subscribe(data=>{
      console.log("...+")
      console.log(data);
      this.volunteerArray.push(data);
      this.routerService.routeTovolunteerDashboard();

    },
    error=>{
      console.log(error);
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
    
        this.http.post('http://localhost:8080/upload-file', file, { observe: 'response' })
    
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
        volunteer_name: 'Enter a valid Name',
        volunteer_email: 'Enter a valid email address (username@gmail.com)',
        volunteer_mobileNumber: 'Enter 10 digit mobile number',
       
      };
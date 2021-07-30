import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm, FormGroup, FormControl, Validators } from '@angular/forms';
import { Donor } from 'src/app/Model/donor';
import { Mail } from 'src/app/Model/Mail';
import { DonorService } from 'src/app/services/donor.service';
import { OtpService } from 'src/app/services/otp.service';
import { RouterService } from './../../services/router.service';

interface BloodGroup {
  bloodType: string;
  bloodName: string;
}


@Component({
  selector: 'app-donor-registeration',
  templateUrl: './donor-registeration.component.html',
  styleUrls: ['./donor-registeration.component.css']
})
export class DonorRegisterationComponent implements OnInit {

  bloodGroups: BloodGroup[] = [
    {bloodType: 'A+', bloodName: 'A+'},
    {bloodType: 'A-', bloodName: 'A-'},
    {bloodType: 'B+', bloodName: 'B+'},
    {bloodType: 'B-', bloodName: 'B-'},
    {bloodType: 'AB+', bloodName: 'AB+'},
    {bloodType: 'AB-', bloodName: 'AB-'},
    {bloodType: 'O-', bloodName: 'O-'},
    {bloodType: 'O+', bloodName: 'O+'},
  ];

  errors = errorMessages;
  public donor: Donor[];
  optmessage:String;
  otpNumber:Number;
  myemail:Mail=new Mail();
  constructor(private donorService :DonorService, private otpService: OtpService,private routerService:RouterService ){}
  donorArray:Array<Donor>=[]; 

 
  donorform: FormGroup;

  ngOnInit() {

  this.donorform = new FormGroup({
    'name' : new FormControl(null,[Validators.required, Validators.minLength(4),Validators.pattern('^[A-Za-z\s]{1,}[\.]{0,1}[A-Za-z\s]{0,}$')]),
    'age' : new FormControl(null,[Validators.required,Validators.pattern('^(0?[1-9]|[1-9][0-9]|[1][1-9][1-9]|200)$')]),
    'emailId' : new FormControl(null, [Validators.required, Validators.email]),
    'phoneNumber': new FormControl('',[Validators.required,
      Validators.pattern('(0/91)?[7-9][0-9]{9}')
    ]),
      'city' : new FormControl(null, Validators.required),
    'otp' : new FormControl(null, [Validators.required,Validators.maxLength(6), Validators.pattern("^[0-9]*$")]),
    'bloodGroup':new FormControl(null,Validators.required)
  });



  }

  
  get name() {
    return this.donorform.get('name');
  }
  get emailId() {
    return this.donorform.get('emailId');
  }
  get age() {
    return this.donorform.get('age');
  }
  get city() {
    return this.donorform.get('city');
  }
  get bloodGroup() {
    return this.donorform.get('bloodGroup');
  }
  get phoneNumber() {
    return this.donorform.get('phoneNumber');
  }

  
  public getDonorsAvailable(): void{
    console.log(this.donorform.value)
    this.donorService.getDonors().subscribe(
      (response:Donor[])=>{
        this.donor=response;
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
    );
  }

 

  DonorRegister(){
    console.log("asdasdasdasd");
    this.donorService.addDonor(this.donorform.value).subscribe(data=>{
      console.log(data);
      this.donorArray.push(data);
      this.routerService.routeTodonorDashboard();

    },
    error=>{
      console.log(error);
    })

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
 


}
export const errorMessages: { [key: string]: string } = {
  name: 'Full name must be between 1 and 128 characters',
  emailId: 'Email must be a valid email address (username@domain)',
  phoneNumber: 'Please provide correct mobile number (length should be 10)',
  age:'Age must be greater than 18'
};
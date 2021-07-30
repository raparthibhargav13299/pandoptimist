import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Mail } from 'src/app/Model/Mail';
import { OtpService } from 'src/app/services/otp.service';

@Component({
  selector: 'app-otp-form',
  templateUrl: './otp-form.component.html',
  styleUrls: ['./otp-form.component.css']
})
export class OtpFormComponent implements OnInit {

  optmessage:String;
  otpNumber:Number;
  myemail:Mail=new Mail();

  constructor( private otpService: OtpService) { }

  ngOnInit(): void {
  }
  otpForm = new FormGroup({
    emailId : new FormControl(null, [Validators.required, Validators.email]),
    otp : new FormControl(null, [Validators.required,Validators.maxLength(6), Validators.pattern("^[0-9]*$")]),
   
  });

  get emailId() {
    return this.otpForm.get('emailId');
  }
  register(){
      console.log("inside register")
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

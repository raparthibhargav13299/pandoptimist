import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Mail } from 'src/app/Model/Mail';
import { RegisteredUser } from 'src/app/registered-user';
import { LoginuserService } from 'src/app/services/loginuser.service';
import { OtpService } from 'src/app/services/otp.service';


@Component({
  selector: 'app-patientlogin',
  templateUrl: './patientlogin.component.html',
  styleUrls: ['./patientlogin.component.css']
})
export class PatientloginComponent implements OnInit {

  otpmail: Mail = new Mail();
  otpmessage: boolean = false;
  hide: boolean = true;
  regUserotp: RegisteredUser = new RegisteredUser();
  otpVerified: boolean = false;
  otpinvalid = false;

  constructor(private fb: FormBuilder, private router: Router,
    private otpService: OtpService, private loginservice: LoginuserService) { }

  ngOnInit(): void {
  }



  get email() { return this.otploginForm.get('email') }
  get otp() { return this.otploginForm.get('otp') }

  public otploginForm = this.fb.group({
    email: ['', [Validators.required, Validators.pattern("^[a-zA-Z0-9+_.-]+@[a-z]+\.[.]com$")]],
    otp: ['', [Validators.required, Validators.pattern("^[0-9].{0,5}$")]],
  });

  sendOtp() {
    this.otpmail.mail = this.email.value;
    this.otpService.sendMailForOtp(this.otpmail).subscribe((data) => {
      this.otpmessage = true;
      console.log(this.otpmessage);

    })
  }
  verifyOtp() {
    this.otpinvalid = false;

    this.otpService.OtpVerification(this.otp.value, this.email.value).subscribe((data) => {
      if (data == "OTP IS VALID") {
        this.otpVerified = true;
        localStorage.setItem('email', this.email.value);
        localStorage.setItem('role', "patient");
      }
      else {
        this.otpinvalid = true;
      }

    }, (error) => {
      console.log(error)
    })
  }

  onSubmitLogin() {
    console.log(this.email.value);
    this.router.navigate(['/patient-dashboard']);

    // this.loginservice.loggedinuser = this.regUserotp;

    // if (this.otploginForm.valid) {
    //   this.loginservice.generateJwtToken(this.regUserotp).subscribe((token: any) => {

    //     this.loginservice.setToken(token.headers.get('token'));

    //     console.log(token.headers.get('role'));

    //     setTimeout(() => {
    //       if (!token.headers.get('role') && token.headers.get('token')) {
    //         this.otploginForm.reset();
    //         this.router.navigate(['/role']);
    //       } else if (token.headers.get('token')) {
    //         this.otploginForm.reset();
    //         if (token.headers.get('role') == 'doctor') {
    //           this.router.navigate(['/doctor-registration']);
    //         } else if (token.headers.get('role') == 'donor') {
    //           this.router.navigate(['/donor-register']);
    //         } else if (token.headers.get('role') == 'volunteer') {
    //           this.router.navigate(['/volunteer-register']);
    //         } 
    //       }
    //     }, 3000);

    //     error => {
    //       console.log(error);
    //     }
    //   });
    // }
    // else {
    //   alert('Invalid, please give valid credentials');
    // }

  }

}

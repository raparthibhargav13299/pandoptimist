import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DoctorRedis } from 'src/app/Model/DoctorRedis';
import { RegisteredUser } from 'src/app/registered-user';
import { LoginuserService } from 'src/app/services/loginuser.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  hide: boolean = true;
  regUser1: RegisteredUser = new RegisteredUser();
  success: boolean = false;
  invalidform: boolean = false;
  doctorOnline:DoctorRedis = new DoctorRedis();



  get email() { return this.loginForm.get('email') }
  get password() { return this.loginForm.get('password') }

  public loginForm = this.fb.group({
    email: ['', [Validators.required, Validators.pattern("^[a-zA-Z0-9+_.-]+@[a-z]+\.[.]com$")]],
    password: ['', [Validators.required, Validators.pattern("^[a-z0-9].{8,20}$")]],
  });
  constructor(private fb: FormBuilder, private loginService: LoginuserService, private router: Router) { }


  ngOnInit() {
    this.invalidform = false;
    this.success = false;
  }


  onSubmitLogin() {
    this.invalidform = false;
    this.regUser1 = this.loginForm.value;
    this.loginService.loggedinuser = this.regUser1;
    if (this.loginForm.valid) {
      this.loginService.generateJwtToken(this.regUser1).subscribe(
        (token: any) => {
          this.success = true;
          localStorage.setItem('role', token.headers.get('role'));
          localStorage.setItem('email', token.headers.get('email'));
          this.loginService.setToken(token.headers.get('token'));
          if (localStorage.getItem('role') == 'doctor') {
            this.doctorOnline.email=localStorage.getItem('email');
            this.doctorOnline.status="online";
            this.loginService.updateStatusOnline(this.doctorOnline).subscribe();
          }
          setTimeout(() => {
            if (!token.headers.get('role') && token.headers.get('token')) {
              this.loginForm.reset();
              this.router.navigate(['/role']);
            } else if (token.headers.get('token')) {
              this.loginForm.reset();
              if (token.headers.get('role') == 'doctor') {
                this.router.navigate(['/doctor-registration']);
              } else if (token.headers.get('role') == 'donor') {
                this.router.navigate(['/donor-register']);
              } else if (token.headers.get('role') == 'volunteer') {
                this.router.navigate(['/volunteer-register']);
              }
            }
          }, 3000);
          error => {
            console.log(error);
          }
        });
    }
    else {
      this.invalidform = true;
    }
  }
}

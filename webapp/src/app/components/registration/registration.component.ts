import { ValueConverter } from '@angular/compiler/src/render3/view/template';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisteredUser } from 'src/app/registered-user';
import { RegisteruserService } from 'src/app/services/registeruser.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  regUser: RegisteredUser = new RegisteredUser();
  hide: boolean = true;
  registered: any;
  success: boolean = false;
  invalidform: boolean = false;

  constructor(private fb: FormBuilder,
    private registerservice: RegisteruserService,
    private router: Router) { }

  public registerForm = this.fb.group({
    email: ['', [Validators.required, Validators.pattern("^[a-zA-Z0-9+_.-]+@[a-z]+\.[.]com$")]],
    password: ['', [Validators.required, Validators.pattern("^[a-z0-9].{8,20}$")]],
  });

  ngOnInit(): void {
    this.success = false;
    this.invalidform = false;

  }

  onSubmit() {
    this.invalidform = false;
    if (this.registerForm.valid) {
      this.registerservice.registerUser(this.registerForm.value).subscribe(
        (data) => {
          this.success = true;
          this.regUser = this.registerForm.value;

          error => {

          }
        });
      setTimeout(() => {
        this.goToLogin();
      }, 3000);

    } else {
      this.invalidform = true;
    }
  }

  goToLogin() {
    this.router.navigate(['/login']);
  }

  get email() { return this.registerForm.get('email') }
  get password() { return this.registerForm.get('password') }

}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RegisteredUser } from 'src/app/registered-user';
import { LoginuserService } from 'src/app/services/loginuser.service';

interface Role {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-role',
  templateUrl: './role.component.html',
  styleUrls: ['./role.component.css']
})
export class RoleComponent implements OnInit {

  constructor(private loginservice: LoginuserService, private router: Router) { }


  roles: Role[] = [
    { value: 'doctor', viewValue: 'Doctor' },
    { value: 'donor', viewValue: 'Donor' },
    { value: 'volunteer', viewValue: 'Volunteer' }
  ];

  regUser: RegisteredUser;
  selectedRole: any;
  loginuser1: RegisteredUser = new RegisteredUser();


  ngOnInit(): void {
  }

  role(e) {
    this.selectedRole = e.value;
  }

  selectRole() {
    this.loginservice.loggedinuser.role = this.selectedRole;
    this.loginservice.roleUpdate(this.loginservice.loggedinuser);

    if (this.loginservice.getToken()) {
      if (this.selectedRole == 'doctor') {
        this.router.navigate(['/doctor-registration']);
      } else if (this.selectedRole == 'donor') {
        this.router.navigate(['/donor-register']);
      } else if (this.selectedRole == 'volunteer') {
        this.router.navigate(['/volunteer-register']);
      }

    } else {
      this.router.navigate(['/login']);
    }
  }

}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginuserService } from 'src/app/services/loginuser.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private loginservice: LoginuserService, private router: Router) { }

  public loggedIn = false;

  ngOnInit(): void {
    this.loggedIn = this.loginservice.isLoggedin();
  }

  toSOS() {
    this.router.navigate(['/patient-login']);
  }

  toHome() {
    this.router.navigate(['/home'])
  }


  logout() {
    this.loginservice.logout();
    this.loggedIn = this.loginservice.isLoggedin();
    location.reload();
  }

}

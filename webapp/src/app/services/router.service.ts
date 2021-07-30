import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RouterService {

  constructor(private router: Router){}
  
  routeToDashboard() {
    this.router.navigateByUrl('/doctor-dashboard');
  }

  routeToRegistration() {
    this.router.navigateByUrl('/doctor-registration');
  }
  routeTovolunteerDashboard() {
    this.router.navigateByUrl('/volunteer-dashboard');
  }
  routeTovolunteerRegistration() {
    this.router.navigateByUrl('/volunteer-register');
  }
  routeTodonorDashboard() {
    this.router.navigateByUrl('/donor-dashboard');
  }
  routeTodonorRegistration() {
    this.router.navigateByUrl('/donor-register');
  }

}

import { BrowserModule } from '@angular/platform-browser';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { HttpClientModule } from "@angular/common/http";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ConsultantformComponent } from './components/consultantform/consultantform.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatCardModule, MatCardTitle,MatCardImage} from '@angular/material/card';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
// import {MatCardImageModule} from '@angular/material/'
// import {MatCardTitleModule}  from '@angular'
import { DocterRegistrationComponent } from './components/docter-registration/docter-registration.component';
// import {ReactiveFormsModule} from '@angular/forms';
import { DoctorService } from './services/doctor.service';
import { Doctor } from './Model/Doctor';
// import { HttpClientModule } from '@angular/common/http';
import { DoctorDashboardComponent } from './components/doctor-dashboard/doctor-dashboard.component';
import { CanActivateGuard } from './guards/can-activate.guard';
import { RegistrationComponent } from './components/registration/registration.component';
import { LoginComponent } from './components/login/login.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button'
import { HomeComponent } from './components/home/home.component';
import { PlasmaRequestComponent } from './components/plasma-request/plasma-request.component';
import { VolunteerRegistrationComponent } from './components/volunteer-registration/volunteer-registration.component';
import { VolunteerDashboardComponent } from './components/volunteer-dashboard/volunteer-dashboard.component';
import { DonorRegisterationComponent } from './components/donor-registeration/donor-registeration.component';
import { DonorDashboardComponent } from './components/donor-dashboard/donor-dashboard.component';
import { MatRadioModule } from '@angular/material/radio';
// import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { OtpFormComponent } from './components/otp-form/otp-form.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import { LoginuserService } from './services/loginuser.service';
import { RegisteruserService } from './services/registeruser.service';
import { AuthGuard } from './auth.guard';
import { RoleComponent } from './components/role/role.component';
import { PatientloginComponent } from './components/patientlogin/patientlogin.component';
// import { MatButtonModule } from '@angular/material/button';
import {MatTabsModule} from '@angular/material/tabs';

import { PatientDashboardComponent } from './components/patient-dashboard/patient-dashboard.component';

import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { NavComponent } from './components/nav/nav.component';
import { LayoutModule } from '@angular/cdk/layout';

import { DashComponent } from './components/dash/dash.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatMenuModule } from '@angular/material/menu';
import { AddResourceComponent } from './components/add-resource/add-resource.component';
import { VerificationComponent } from './components/verification/verification.component';
import { InformationComponent } from './components/information/information.component';

import {MatExpansionModule} from '@angular/material/expansion';
import { MedicineRequestComponent } from './components/medicine-request/medicine-request.component';
import { BedRequestComponent } from './components/bed-request/bed-request.component';
import { EquipmentRequestComponent } from './components/equipment-request/equipment-request.component';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { SosRequestComponent } from './components/sos-request/sos-request.component';
import { CKEditorModule } from '@ckeditor/ckeditor5-angular';
import { SearchResourceComponent } from './components/search-resource/search-resource.component';
// import { CKEditorModule } from '@ckeditor/ckeditor5-angular';
import { ChatServiceComponent } from './components/chat-service/chat-service.component';
import { RequestFormComponent } from './components/request-form/request-form.component';
import {MatPaginatorModule} from '@angular/material/paginator';



@NgModule({
  declarations: [
    AppComponent,
    ConsultantformComponent,
    FooterComponent,
    RegistrationComponent,
    DocterRegistrationComponent,
    DoctorDashboardComponent,
    LoginComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    VolunteerRegistrationComponent,
    VolunteerDashboardComponent,
    DonorRegisterationComponent,
    DonorDashboardComponent,
    OtpFormComponent,
    PlasmaRequestComponent,
    RoleComponent,
    PatientDashboardComponent,
    NavComponent,
    DashComponent,
    AddResourceComponent,
    VerificationComponent,
    InformationComponent,
    RequestFormComponent,

    MedicineRequestComponent,
    BedRequestComponent,
    EquipmentRequestComponent,
    ChatServiceComponent,
    SosRequestComponent,
    PatientloginComponent,
    SearchResourceComponent

  ],
  imports: [
    CKEditorModule,
    BrowserModule,
    AppRoutingModule,
    MatToolbarModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatFormFieldModule,
    MatIconModule,
    MatButtonModule,
    FormsModule,
    MatCheckboxModule,
    ReactiveFormsModule,
    MatSelectModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatRadioModule,
    MatCardModule,
    MatTabsModule,

    MatSidenavModule,
    MatListModule,
    LayoutModule,
    MatGridListModule,
    MatMenuModule,
    MatExpansionModule,
    MatPaginatorModule

  ],
  providers: [DoctorService,Doctor,CanActivateGuard, LoginuserService, RegisteruserService, AuthGuard],
  bootstrap: [AppComponent],
  schemas:[
    CUSTOM_ELEMENTS_SCHEMA
  ]
})
export class AppModule { }

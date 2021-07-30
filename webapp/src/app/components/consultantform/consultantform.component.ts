import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { ConsultantServiceService } from 'src/app/services/consultant-service.service';
import { DoctorRedis } from './../../Model/DoctorRedis';
import { Observable } from 'rxjs/internal/Observable';
import { Doctor } from 'src/app/Model/Doctor';
import { Client } from '@stomp/stompjs';
import { MessageModel } from 'src/app/Model/message';
import * as SockJS from 'sockjs-client';  
import { LoginuserService } from 'src/app/services/loginuser.service';



@Component({
  selector: 'app-consultantform',
  templateUrl: './consultantform.component.html',
  styleUrls: ['./consultantform.component.css']
})
export class ConsultantformComponent implements OnInit {
  Display: boolean = false;
  public doctors: any;
  doctorsOnline: Array<DoctorRedis> = new Array<DoctorRedis>();
  pageSlice:any; 

  emailId=localStorage.getItem('email')
  private client: Client;
  public connected: boolean;
  public writingvalue: string;
  public clientId: string;
  public message: MessageModel = new MessageModel();
  public messages: any[] = [];
  public messgaeTyped:string="";
  messageto:string="";

  constructor(private service: ConsultantServiceService, private loginService:LoginuserService) {
    this.clientId = 'id-' + new Date().getTime() + '-' + Math.random().toString(36).substr(2);
   }
 








  ngOnInit(): void {
    this.getdoctors();

    // patientchatbot
    console.log("--->"+this.messageto);
    this.client = new Client();
    this.client.webSocketFactory= () => {
      return new SockJS('http://localhost:8095/chat');
    }
    
    this.client.onConnect = ( frame ) => {
      
      console.log('Connected: ' + this.client.connected + ' : ' + frame);
      this.connected = true;
      this.client.subscribe('/topic/messages/'+this.emailId, e => { 
        let message: MessageModel = JSON.parse(e.body) as MessageModel;
        message.date = new Date(message.date);
        console.log("this is subscribe chat messga"+this.message);
        if(!this.message.color && message.type == "CONNECTED" 
            && this.emailId == message.username) {
          this.message.color = message.color;
        }
        this.messages.push(message);
      });

    this.client.subscribe('/topic/writing', e => {
        console.log("this is subscribe chat writing ");
        this.writingvalue= e.body;
        setTimeout(() => this.writingvalue = '', 3000);
      });

      this.client.subscribe('/topic/history/' + this.messageto, e => {
        console.log("this is subscribe chat/history/ messgsing ");
        const history: MessageModel[] = JSON.parse(e.body) as MessageModel[];
        this.messages = history.map(m => {
          m.date = new Date(m.date);
          return m;
        }).reverse();
      });
      this.client.publish({destination: '/chat/history', body: this.messageto});

      this.message.type = "CONNECTED";
      this.client.publish({destination: '/chat/message', body: JSON.stringify(this.message)});
    }

    this.client.onDisconnect = ( frame ) => {
      console.log('Disconnected: ' + !this.client.connected + ' : ' + frame);
      this.message = new MessageModel();
      this.messages = [];
      this.connected = false;
    }

    this.client.activate();
  } 
  // connect() {
  //   this.client.activate();
  // }l̥

  // disconnect() {
  //   this.client.deactivate();
  // }

  isShow = false;
 
  toggleDisplay() {
    this.isShow = !this.isShow;
  }

  writing() {
    this.client.publish({destination: '/topic /writing', body: this.emailId});
  }


  send() {
    const  message={
      message:this.messgaeTyped,
      username:this.emailId,
      date : new Date().getTime(),
      type : "MESSAGE",
    };
    this.client.publish({destination: '/app/chat/'+this.messageto, body: JSON.stringify(message)});
    this.messages.push(message);
    this.message.message =' ';
    
  }


 // End offff Patientchatbot


  onPageChange(event: PageEvent) {
    const startInd = event.pageIndex * event.pageSize;
    let endInd = startInd + event.pageSize;
    if (endInd > this.doctorsOnline.length) {
      endInd = this.doctorsOnline.length;
    }
    this.pageSlice = this.doctorsOnline.slice(startInd, endInd);
  }

  connectAvilable(doctor) {
    console.log(doctor);
    this.messageto=doctor.email;
    this.service.doctorEngaged=doctor;
    this.loginService.setStatusBusy(doctor).subscribe();
    this.getdoctors();
  }

  getdoctors() {
    this.service.getAllDoctorsOnline().subscribe(
      (data) => {
        this.doctorsOnline = data;
        this.pageSlice= this.doctorsOnline.slice(0, 16);

        error => {
          this.Display = true;
        }
      });
  }

  



}

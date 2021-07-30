import { Component, AfterViewInit, OnInit } from '@angular/core';

import { FormGroup, FormControl, AbstractControl, Validators } from '@angular/forms';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { HttpClient } from '@angular/common/http';
import { DoctorService } from './../../services/doctor.service';
import { Doctor } from './../../Model/Doctor';
import { DoctorRedis } from './../../Model/DoctorRedis';
import { HealthTips } from './../../Model/HealthTips';

import { Client } from '@stomp/stompjs';
import { MessageModel } from 'src/app/Model/message';
import * as SockJS from 'sockjs-client';  
import { ConsultantServiceService } from 'src/app/services/consultant-service.service';
import { LoginuserService } from 'src/app/services/loginuser.service';

// const ClassicEditor = CKSource.ClassicEditor;
@Component({
  selector: 'app-doctor-dashboard',
  templateUrl: './doctor-dashboard.component.html',
  styleUrls: ['./doctor-dashboard.component.css']
})
export class DoctorDashboardComponent implements OnInit {
	public Editor = ClassicEditor;
	onlineDoctorArray:Array<Doctor>=[];
	doctorArray:Array<Doctor>=[];
	healthTipsArray:	Array<HealthTips>=[];
	condition:boolean=false;




	// chatbot for doctor 
	emailId=localStorage.getItem('email')
	private client: Client;
	public connected: boolean;
	public writingvalue: string;
	public clientId: string;
	public message: MessageModel = new MessageModel();
	public messages: any[] = [];
	public messgaeTyped:string="";
	messageto:string="";

  constructor(private http: HttpClient,private service:DoctorService, private consultantService:ConsultantServiceService, private loginservice:LoginuserService) {
	this.clientId = 'id-' + new Date().getTime() + '-' + Math.random().toString(36).substr(2);
   }
	ngOnInit(): void {

		    // doctorchatbot
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
				this.messageto=message.username
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
	// 	this.client.activate();
	//   }l̥
	
	//   disconnect() {
	// 	this.client.deactivate();
	//   }
	
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
	
	
	 // End offff Doctorchatbot
	
	


	public healthTipsForm = new FormGroup( {
		name: new FormControl(null,[Validators.required, Validators.minLength(4),Validators.pattern('^[a-zA-Z]+( [a-zA-Z]+)+$')]),
		adviceOn: new FormControl(null,[Validators.required, Validators.minLength(5),Validators.pattern('^[a-zA-Z]+( [a-zA-Z]+)+$')]),
		specialist: new FormControl(null,[Validators.required, Validators.minLength(5),Validators.pattern('^[a-zA-Z]+( [a-zA-Z]+)+$')]),
		tips: new FormControl('',Validators.maxLength(300))
	} );

	public formDataPreview?: string;



	getDoctor(){
		this.service.getDoctors().subscribe((data)=>{
			console.log(data);
		this.doctorArray=data;
		},(error)=>{
		  console.log("Error!!!!!!")
		})
	  }
	

	 onSubmit(){
		 console.log("i am submiitng")
		this.service.addHealthTips(this.healthTipsForm.value).subscribe((data)=>{
			console.log(data)
			this.healthTipsArray.push(data);
			this.condition=true;
			},(error)=>{
			  console.log("Error!!!!!!")
			})
	  }

	  makeMeOnline(){
		this.loginservice.updateStatusOnline(this.consultantService.doctorEngaged).subscribe();

	  }

	  loggingOff(){
		  this.loginservice.setStatusBusy(this.consultantService.doctorEngaged).subscribe();
		
	  }

		  
	getallHealthTips(){
		console.log(this.healthTipsForm.value)
		this.service.getHealthTips().subscribe((data)=>{
		this.healthTipsArray=data;
		},(error)=>{
		  console.log("Error!!!!!!")
		})
	  }

	public reset(): void {
		this.healthTipsForm!.reset();
	}

	public get description(): AbstractControl {
		return this.healthTipsForm!.controls.description;
	}



	
  
  
}
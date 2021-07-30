import { Component, OnInit } from '@angular/core';
import { Client } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';  
import { MessageModel } from 'src/app/Model/message';


@Component({
  selector: 'app-chat-service',
  templateUrl: './chat-service.component.html',
  styleUrls: ['./chat-service.component.css']
})
export class ChatServiceComponent implements OnInit {

  private client: Client;
  public connected: boolean;
  public writingvalue: string;
  public clientId: string;

  public message: MessageModel = new MessageModel();
  public messages: any[] = [];
  public messgaeTyped:string="";
  messageto:string="";

  constructor() {
    this.clientId = 'id-' + new Date().getTime() + '-' + Math.random().toString(36).substr(2);
  }

  ngOnInit() {
    console.log("--->"+this.messageto);
    this.client = new Client();
    this.client.webSocketFactory= () => {
      return new SockJS('http://localhost:8095/chat');
    }
      

    this.client.onConnect = ( frame ) => {
      console.log('Connected: ' + this.client.connected + ' : ' + frame);
      this.connected = true;
      this.client.subscribe('/topic/messages/'+this.message.username, e => { 
        let message: MessageModel = JSON.parse(e.body) as MessageModel;
        message.date = new Date(message.date);
        console.log("this is subscribe chat messga"+this.message);
        if(!this.message.color && message.type == "CONNECTED" 
            && this.message.username == message.username) {
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
  }

  connect() {
    this.client.activate();
  }l̥

  disconnect() {
    this.client.deactivate();
  }

  isShow = false;
 
  toggleDisplay() {
    this.isShow = !this.isShow;
  }

  writing() {
    this.client.publish({destination: '/topic /writing', body: this.message.username});
  }


  send() {
    const  message={
      message:this.messgaeTyped,
      username:this.message.username,
      date : new Date().getTime(),
      type : "MESSAGE",
    };
    this.client.publish({destination: '/app/chat/'+this.messageto, body: JSON.stringify(message)});
    this.messages.push(message);
    this.message.message =' ';
    
  }
}
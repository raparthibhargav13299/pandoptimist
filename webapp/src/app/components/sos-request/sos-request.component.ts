import { Component, OnInit } from '@angular/core';
import { EmailQueue } from 'src/app/Model/EmailQueue';
import { MedicalRequest } from 'src/app/Model/MedicalResquest';
import { Requirement } from 'src/app/Model/Requirement';
import { SendResourceService } from 'src/app/services/send-resource.service';
import { SOSRequestService } from 'src/app/services/sosrequest.service';

@Component({
  selector: 'app-sos-request',
  templateUrl: './sos-request.component.html',
  styleUrls: ['./sos-request.component.css']
})
export class SosRequestComponent implements OnInit {
  constructor(private service: SOSRequestService,private resService:SendResourceService) { }
myRequest: MedicalRequest= new MedicalRequest;
comments:String
emailQueue:EmailQueue;
// requirmentArr: Requirement= new Requirement;
myrequirment: Array<Requirement> = new Array();
  ngOnInit(): void {
    this.service.getRandomRequests().subscribe(data=>{
      console.log(data)

        this.myRequest=data;       
        console.log(this.myRequest)
        
    }, error=>{
      console.log("error")
    })
  }


  closeRequest(){
    // console.log(this.comments)
    // console.log(this.myRequest.id)
    // this.emailQueue.body=this.comments,
   this.emailQueue=new EmailQueue(this.myRequest.email,this.comments);
   console.log(this.emailQueue);
   this.resService.addResource(this.emailQueue,this.myRequest.id,this.comments,this.myRequest.email).subscribe(data=>{
      alert("closed Reuest!!")
      console.log(data)
      this.comments='';
   },error=>{
     console.log(error)
   })
  }
 
  PassRequest(){
    this.resService.passRequest(this.myRequest.id,this.myRequest).subscribe(data=>{
      console.log(data)
      alert("Request Passed")
      this.comments='';
    },error=>{
      console.log(error)
    })
  }
}

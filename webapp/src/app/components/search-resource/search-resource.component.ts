import { Component, OnInit } from '@angular/core';
import { Resource } from 'src/app/Model/Resource';
import { ResourceService } from 'src/app/services/resource.service';

@Component({
  selector: 'app-search-resource',
  templateUrl: './search-resource.component.html',
  styleUrls: ['./search-resource.component.css']
})
export class SearchResourceComponent implements OnInit {

  resourceName="";
  cityName="";
  resultArray: Array<Resource> = [];
  constructor(private service:ResourceService) { }

  ngOnInit(): void {
  }

  search(){
    console.log(this.resourceName+" "+this.cityName);
    this.service.searchResource(this.resourceName,this.cityName).subscribe(data=>{
      console.log("%%%%%%%%%%%%"+data);
      this.resultArray=data;
      
      console.log(this.resultArray);
      this.resourceName='';
      this.cityName='';
    },error=>{
      console.log(error)
    })
  }

}

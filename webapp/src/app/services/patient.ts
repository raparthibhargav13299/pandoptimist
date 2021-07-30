import { Requirements } from "./requirement";


export class Patient{
   
    
    public name :String;
    public gender:String;
    public phoneNumber:String;
    public email :String;
    public hospitalized:String;
    public city:String;
    public requirement: Requirements=new Requirements();
    public uploadPrescription:String
    
    
}
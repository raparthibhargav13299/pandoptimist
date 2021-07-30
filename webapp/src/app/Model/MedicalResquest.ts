import { Requirement } from "./Requirement";

export class MedicalRequest{

  
    id : Number;
    name:String;
    gender:String;
    phoneNumber:String;
    email:String;
     hospitalized:String;
     city:String;

    requirement:Array<Requirement> ;
//     Requirement requirement;

    uploadPrescription:String;
    status:String;
}
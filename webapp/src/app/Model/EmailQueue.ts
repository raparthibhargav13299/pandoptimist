export class EmailQueue{

    mailId:String;
    body:String

    constructor(mailId:String,body:String){
        this.mailId=mailId,
        this.body=body
    }

    set MailId(mailId:String){
        this.mailId=mailId

    }
    set Body(body:String){
        this.body=body

    }
}

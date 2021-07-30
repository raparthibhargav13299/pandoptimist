export class MessageModel {

    message: string;
    date: Date;
    username: string;
    type: string;
    color: string;

    constructor(message?: MessageModel) {

        this.message = '';
        this.date = null;
        this.username = '';
        this.type = '';
        this.color = '';

        if (message) {
            Object.assign(this, message);
        }
    }
}

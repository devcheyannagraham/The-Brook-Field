export class UserDto {
    uuid: string;
    userId:string;
    email: string;
    password:string;

    constructor(email:string, pwd:string){
        this.email = email;
        this.password = pwd;
    }
}
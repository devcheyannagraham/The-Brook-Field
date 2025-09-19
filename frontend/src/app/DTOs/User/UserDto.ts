export class UserDto {
    userId:number;
    email: string;
    password:string;

    constructor(email:string, pwd:string){
        this.email = email;
        this.password = pwd;
    }
}
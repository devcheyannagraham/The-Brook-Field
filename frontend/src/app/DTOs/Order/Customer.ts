export class Customer {
  public id: number;
  public firstName: string;
  public lastName: string;
  public email: string;
  public phoneNumber: string;
  public city: string;
  public state: string;
  public zipCode: number;
  public country: string;
  public address:string;

  constructor(formData:any){
    this.firstName = formData["firstName"];
    this.lastName = formData["lastName"];
    this.email = formData["email"];
    this.phoneNumber = formData["firstName"];
    this.city = formData["city"];
    this.state = formData["state"];
    this.zipCode = formData["zipCode"];
    this.country = formData["country"];
    this.address = formData["address"]
  }
}

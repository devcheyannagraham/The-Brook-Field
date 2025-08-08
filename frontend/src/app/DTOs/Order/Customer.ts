export class Customer {
  public id: Number;
  public firstName: String;
  public lastName: String;
  public email: String;
  public phoneNumber: String;
  public city: String;
  public state: String;
  public zip: Number;
  public country: String;

  constructor(formData:any){
    this.firstName = formData["firstName"];
    this.lastName = formData["lastName"];
    this.email = formData["email"];
    this.phoneNumber = formData["firstName"];
    this.city = formData["city"];
    this.state = formData["state"];
    this.zip = formData["zip"];
    this.country = formData["country"];
  }
}

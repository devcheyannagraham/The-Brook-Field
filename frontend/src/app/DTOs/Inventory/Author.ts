
export class Author {
  public id: number;
  public firstName: string;
  public lastName: string;

  constructor(...data:any){
    data = data[0];
    if(data){
      this.id = data["id"] || null;
      this.firstName = data["firstName"] || null;
      this.firstName = data["firstName"] || null;
    }
  }
}


export class Author {
  public id:Number;
  public firstName:String;
  public lastName:String;

  constructor(...data:any){
    data = data[0];
    if(data){
      this.id = data["id"] || null;
      this.firstName = data["firstName"] || null;
      this.firstName = data["firstName"] || null;
    }
  }
}

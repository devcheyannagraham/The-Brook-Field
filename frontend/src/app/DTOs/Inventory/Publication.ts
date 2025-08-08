import {Author} from './Author';

export class Publication {
  public publicationId: Number;
  public datePublished: Date;
  public isbn: String;
  public genre: String;
  public quantity: Number; // May need to delete
  public title: String;
  public author: Author;

  constructor(...data:any){
    data = data[0];
    if(data){
      this.publicationId = data["publicationId"] || null;
      this.datePublished = data["datePublished"] || null;
      this.isbn = data["isbn"] || null;
      this.genre = data["genre"] || null;
      this.quantity = data["quantity"] || null;
      this.title = data["title"] || null;
      this.author = data["author"] || null;
    }
  }

}

import { SVGIcon } from '../SVGIcon';
import { Author } from './Author';

export class Publication {
  public publicationId: number;
  public datePublished: Date;
  public isbn: string;
  public genre: string;
  public quantity: number; // May need to delete
  public title: string;
  public author: Author;
  public svgIcon: SVGIcon;

  constructor(...data: any) {
    data = data[0];
    if (data) {
      this.publicationId = data["publicationId"] || null;
      this.datePublished = data["datePublished"] || null;
      this.isbn = data["isbn"] || null;
      this.genre = data["genre"] || null;
      this.quantity = data["quantity"] || null;
      this.title = data["title"] || null;
      this.author = data["author"] || null;
      this.svgIcon = data["svgIcon"] || null;
    }
  }

}

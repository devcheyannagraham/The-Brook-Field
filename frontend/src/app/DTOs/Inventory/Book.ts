import {PublicationItem} from './PublicationItem';
import {PublicationItemType} from '../../Enums/PublicationItemType';

export class Book extends PublicationItem {

  constructor(...data:any) {
    super(data[0])
    this.publicationItemType = PublicationItemType.BOOK;
  }
}

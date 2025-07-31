import {PublicationItem} from './PublicationItem';
import {PublicationItemType} from '../../Enums/PublicationItemType';

export class Book extends PublicationItem {

  constructor() {
    super()
    this.publicationItemType = PublicationItemType.BOOK;
  }
}

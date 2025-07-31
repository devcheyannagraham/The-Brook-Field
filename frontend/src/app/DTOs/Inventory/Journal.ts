import {PublicationItem} from './PublicationItem';
import {PublicationItemType} from '../../Enums/PublicationItemType';

export class Journal extends PublicationItem {
  public issueDate: Date;
  public issueNumber: Number;
  public issueName: String;
  public volume: String;

  constructor() {
    super();
    this.publicationItemType = PublicationItemType.JOURNAL;
  }
}

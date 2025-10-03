import {PublicationItem} from './PublicationItem';
import {PublicationItemType} from '../../Enums/PublicationItemType';

export class Journal extends PublicationItem {
  public issueDate: Date;
  public issueNumber: string;
  public issueName: string;
  public volume: string;

  constructor(...data: any) {
    data = data[0];
    super(data);
    if (data) {
      this.issueNumber = data["issueNumber"] || null;
      this.issueName = data["issueName"] || null;
      this.issueDate = data["issueDate"] || null;
      this.volume = data["volume"] || null;
    }
    this.publicationItemType = PublicationItemType.JOURNAL;
  }


}

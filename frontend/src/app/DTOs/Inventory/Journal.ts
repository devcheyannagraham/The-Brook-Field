import {PublicationItem} from './PublicationItem';
import {PublicationItemType} from '../../Enums/PublicationItemType';

export class Journal extends PublicationItem {
  public issueDate: Date;
  public issueNumber: Number;
  public issueName: String;
  public volume: String;

  constructor(...data: any) {
    data = data[0];
    super(data);
    if (data) {
    console.log("data in journal", data)
      this.issueNumber = data["issueNumber"] || null;
      this.issueName = data["issueName"] || null;
      this.issueDate = data["issueDate"] || null;
      this.volume = data["volume"] || null;
    }
    this.publicationItemType = PublicationItemType.JOURNAL;
  }


}

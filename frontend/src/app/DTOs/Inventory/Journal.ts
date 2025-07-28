import { Publication } from './Publication';
import { PublicationItem } from './PublicationItem';

export class Journal extends PublicationItem {
  constructor(formData: {
    itemId: Number;
    itemType: String;
    edition: String;
    format: String;
    publicationItemType: String;
    purchasePrice: Number;
    rentalRate: Number;
    status: String;
    publication: Publication;
    issueDate: Date;
    issueNumber: Number;
    issueName: String;
    volume: String;
  }) {
    super(formData);
    this.formData.publicationItemType = 'JOURNAL';
  }
}

import { Publication } from './Publication';
import { PublicationItem } from './PublicationItem';

export class Book extends PublicationItem {
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
  }) {
    super(formData);
    this.formData.publicationItemType = 'BOOK';
  }
}

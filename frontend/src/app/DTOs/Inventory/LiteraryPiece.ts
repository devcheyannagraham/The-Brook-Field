import { Publication } from './Publication';
import { PublicationItem } from './PublicationItem';

export class LiteraryPiece extends PublicationItem {
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
    this.formData.publicationItemType = 'LITERARY_PIECE';
  }
}

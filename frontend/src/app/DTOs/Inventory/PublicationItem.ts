import { Item } from './Item';
import { Publication } from './Publication';

export class PublicationItem extends Item {
  constructor(
    public formData: {
      itemId: Number;
      itemType: String;
      edition: String;
      format: String;
      publicationItemType: String;
      purchasePrice: Number;
      rentalRate: Number;
      status: String;
      publication: Publication;
    }
  ) {
    super(formData.itemId, formData.itemType);
  }
}

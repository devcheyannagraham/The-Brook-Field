import {Item} from './Item';
import {Publication} from './Publication';
import {ItemType} from '../../Enums/ItemType'
import {PublicationItemType} from '../../Enums/PublicationItemType';
import {PublicationItemStatus} from '../../Enums/PublicationItemStatus';
import {PublicationItemFormat} from '../../Enums/PublicationItemFormat';

export class PublicationItem extends Item {
  public edition: String;
  public format: PublicationItemFormat;
  public publicationItemType: PublicationItemType;
  public purchasePrice: Number;
  public rentalRate: Number;
  public status: PublicationItemStatus;
  public publication: Publication;
  public quantity: Number;


  constructor(...data: any) {
    data = data[0];
    super(data);
    if (data) {
      console.log("DATA IN PUBITEM", data)
      this.edition = data["edition"] || null;
      this.format = data["format"] || null;
      this.publicationItemType = data["publicationItemType"] || null;
      this.purchasePrice = data["purchasePrice"] || null;
      this.rentalRate = data["rentalRate"] || null;
      this.status = data["status"] || null;
      this.publication = data["publication"] || null;
      this.quantity = data["quantity"] || null;
    }
    this.itemType = ItemType.PUBLICATION_ITEM;
  }
}

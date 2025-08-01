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

  constructor() {
    super();
    this.itemType = ItemType.PUBLICATION_ITEM;
  }
}

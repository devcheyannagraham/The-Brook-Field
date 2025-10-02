import {Item} from './Item';
import {Publication} from './Publication';
import {ItemType} from '../../Enums/ItemType'
import {PublicationItemType} from '../../Enums/PublicationItemType';
import {PublicationItemStatus} from '../../Enums/PublicationItemStatus';
import {PublicationItemFormat} from '../../Enums/PublicationItemFormat';
import { SVGIcon } from '../SVGIcon';


export class PublicationItem extends Item {
  public id: number;
  public edition: string;
  public format: PublicationItemFormat;
  public publicationItemType: PublicationItemType;
  public purchasePrice: number;
  public rentalRate: number;
  public publicationItemStatus: PublicationItemStatus;
  public publication: Publication;
  public quantity: number;
  public svgIcon:SVGIcon;


  constructor(...data: any) {
    data = data[0];
    super(data);
    if (data) {
      this.id = data["id"] || null;
      this.edition = data["edition"] || null;
      this.format = data["format"] || null;
      this.publicationItemType = data["publicationItemType"] || null;
      this.purchasePrice = data["purchasePrice"] || null;
      this.rentalRate = data["rentalRate"] || null;
      this.publicationItemStatus = data["publicationItemStatus"] || null;
      this.publication = data["publication"] || null;
      this.quantity = data["quantity"] || null;
      this.svgIcon = data["svgIcon"] || null;
    }
    this.itemType = ItemType.PUBLICATION_ITEM;
  }
}

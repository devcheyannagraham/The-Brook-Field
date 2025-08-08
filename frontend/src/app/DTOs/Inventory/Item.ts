import {ItemType} from '../../Enums/ItemType';

export class Item {
  public itemId: Number;
  public itemType: ItemType;

  constructor(...data: any) {
    data = data[0];
    if (data) {
      this.itemId = data["itemId"] || null;
      this.itemType = data["itemType"] || null;
    }
  }
}

import {Item} from '../Inventory/Item';
import {Accessory} from './Accessory';
import {AccessoryItemStatus} from '../../Enums/AccessoryItemStatus';
import {ItemType} from '../../Enums/ItemType';

export class AccessoryItem extends Item {
  public accessory: Accessory;
  public accessoryItemStatus: AccessoryItemStatus;

  constructor(...data: any) {
    data = data[0];
    super(data);
    if (data) {
      this.accessory = data["accessory"] || null;
      this.accessoryItemStatus = data["accessoryItemStatus"] || null;
    }
      this.itemType = ItemType.ACCESSORY_ITEM;
  }
}

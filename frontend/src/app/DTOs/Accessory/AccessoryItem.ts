import {Item} from '../Inventory/Item';
import {Accessory} from './Accessory';
import {AccessoryItemStatus} from '../../Enums/AccessoryItemStatus';
import {ItemType} from '../../Enums/ItemType';

export class AccessoryItem extends Item {
  public accessory: Accessory;
  public accessoryItemStatus: AccessoryItemStatus;

  constructor() {
    super();
    this.itemType = ItemType.ACCESSORY_ITEM;
  }
}

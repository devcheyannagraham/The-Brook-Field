import {Item} from '../Inventory/Item';
import {Accessory} from './Accessory';
import {AccessoryItemStatus} from '../../Enums/AccessoryItemStatus';

export class AccessoryItem extends Item {
  public accessory: Accessory;
  public accessoryItemStatus: AccessoryItemStatus;

  constructor() {
    super();
  }


}

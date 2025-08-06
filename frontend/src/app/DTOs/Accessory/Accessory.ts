import {AccessoryType} from '../../Enums/AccessoryType';

export class Accessory {
  public accessoryId: Number;
  public accessoryType: AccessoryType
  public accessoryName: String;
  public quantity: Number;
  public price: Number;

  constructor() {
  }
}

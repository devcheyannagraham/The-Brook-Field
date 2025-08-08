import {AccessoryType} from '../../Enums/AccessoryType';

export class Accessory {
  public accessoryId: Number;
  public accessoryType: AccessoryType
  public accessoryName: String;
  public quantity: Number;
  public price: Number;

  constructor(...data: any) {
    data = data[0];
    if (data) {
      this.accessoryId = data["accessoryId"] || null;
      this.accessoryName = data["accessoryName"] || null;
      this.accessoryType = data["accessoryType"] || null;
      this.quantity = data["quantity"] || null;
      this.price = data["price"] || null;
    }
  }
}

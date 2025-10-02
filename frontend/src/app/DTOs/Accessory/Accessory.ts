import {AccessoryType} from '../../Enums/AccessoryType';
import {SVGIcon} from '../SVGIcon';

export class Accessory {
  public accessoryId: number;
  public accessoryType: AccessoryType
  public accessoryName: string;
  public quantity: number;
  public price: number;
  public svgIcon:SVGIcon;

  constructor(...data: any) {
    data = data[0];
    if (data) {
      this.accessoryId = data["accessoryId"] || null;
      this.accessoryName = data["accessoryName"] || null;
      this.accessoryType = data["accessoryType"] || null;
      this.quantity = data["quantity"] || null;
      this.price = data["price"] || null;
      this.svgIcon = data["svgIcon"] || null;
    }
  }
}

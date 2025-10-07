import { ItemType } from "../../Enums/ItemType";
import { SVGIcon } from "../SVGIcon";

export class InventoryCountDto{
    id:number;
    title:string;
    count:number;
    itemType:ItemType;
    svgIcon:SVGIcon
    
}
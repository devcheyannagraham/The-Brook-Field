import { ItemType } from "../../Enums/ItemType";
import { SVGIcon } from "../SVGIcon";

export class PopularItemDto{
    id:number;
    title:string;
    totalUnitsSold:number;
    totalProfit:number;
    itemType:ItemType;
    svgIcon:SVGIcon;
}
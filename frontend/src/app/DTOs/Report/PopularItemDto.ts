import { ItemType } from "../../Enums/ItemType";

export class PopularItemDto{
    id:number;
    title:string;
    totalUnitsSold:number;
    totalProfit:number;
    itemType:ItemType;
}
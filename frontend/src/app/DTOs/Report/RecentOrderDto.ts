import { AccessoryType } from "../../Enums/AccessoryType";
import { Genre } from "../../Enums/Genre";
import { LiteraryType } from "../../Enums/LiteraryType";
import { PublicationItemFormat } from "../../Enums/PublicationItemFormat";
import { PublicationItemType } from "../../Enums/PublicationItemType";
import { TransactionType } from "../../Enums/TransactionType";
import { SVGIcon } from "../SVGIcon";

export class RecentOrderDto {
    orderId:number;
    customerFirstName:string;
    customerLastName:string;
    customerEmail:string;
    orderTotal:number;
    recentOrderTransactions: {
        transactionId:number;
        transactionPrice:number;
        transactionDate: Date;
        transactionType: TransactionType;
        itemId:number;
        publicationId:number;
        publicationItemFormat: PublicationItemFormat;
        publicationItemType: PublicationItemType;
        genre:Genre;
        publicationTitle:string;
        accessoryType: AccessoryType;
        accessoryName: string;
        accessoryId:number;
        svgIcon:SVGIcon;
        literaryType:LiteraryType;
    }[]
}
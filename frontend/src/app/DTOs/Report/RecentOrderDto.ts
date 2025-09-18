import { AccessoryType } from "../../Enums/AccessoryType";
import { Genre } from "../../Enums/Genre";
import { PublicationItemFormat } from "../../Enums/PublicationItemFormat";
import { PublicationItemType } from "../../Enums/PublicationItemType";

export class RecentOrderDto {
    orderId:number;
    customerFirstName:string;
    customerLastName:string;
    customerEmail:string;
    recentOrderTransactions: {
        transactionId:number;
        transactionPrice:number;
        transactionDate: Date,
        itemId:number;
        publicationId:number;
        publicationItemFormat: PublicationItemFormat,
        publicationItemType: PublicationItemType,
        genre:Genre,
        publicationTitle:string,
        accessoryType: AccessoryType
        accessoryName: string
    }
}
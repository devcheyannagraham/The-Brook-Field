import { AccessoryType } from "../../Enums/AccessoryType";
import { Genre } from "../../Enums/Genre";
import { PublicationItemFormat } from "../../Enums/PublicationItemFormat";
import { PublicationItemType } from "../../Enums/PublicationItemType";
import { TransactionType } from "../../Enums/TransactionType";

export class RecentOrderDto {
    orderId:number;
    customerFirstName:string;
    customerLastName:string;
    customerEmail:string;
    orderTotal:number;
    recentOrderTransactions: {
        transactionId:number;
        transactionPrice:number;
        transactionDate: Date,
        transactionType: TransactionType;
        itemId:number;
        publicationId:number;
        publicationItemFormat: PublicationItemFormat,
        publicationItemType: PublicationItemType,
        genre:Genre,
        publicationTitle:string,
        accessoryType: AccessoryType
        accessoryName: string
    }[]
}
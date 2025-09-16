import {Item} from '../Inventory/Item';
import {Order} from './Order';
import {TransactionType} from '../../Enums/TransactionType';

export class Transaction {
  public transactionId: number;
  public transactionType: TransactionType;
  public transactionDate: Date;
  public order: Order;
  public item: Item;
  public transactionPrice: number;
}

import {Transaction} from './Transaction';
import {Customer} from './Customer';

export class Order {
  public id: number;
  public customer: Customer;
  public orderDate: Date;
  public transactions: Transaction[];
  public orderTotal: number;

}

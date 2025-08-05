import {Transaction} from './Transaction';
import {TransactionType} from '../../Enums/TransactionType';

export class Purchase extends Transaction {
  constructor(){
    super();
    this.transactionType = TransactionType.PURCHASE;
  }
}

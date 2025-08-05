import {RentalStatus} from '../../Enums/RentalStatus';
import {Transaction} from './Transaction';
import {TransactionType} from '../../Enums/TransactionType';

export class Rental extends Transaction{
  public startDate: Date;
  public dueDate: Date;
  public rentalStatus: RentalStatus;

  constructor(){
    super();
    this.transactionType = TransactionType.RENTAL;
  }
}

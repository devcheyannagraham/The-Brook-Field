import {RentalStatus} from '../../Enums/RentalStatus';
import {Transaction} from './Transaction';

export class Rental extends Transaction{
  public startDate: Date;
  public dueDate: Date;
  public rentalStatus: RentalStatus;

  constructor(){
    super();
  }
}

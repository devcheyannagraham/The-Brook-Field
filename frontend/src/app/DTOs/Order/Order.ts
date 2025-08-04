export class Order {
  public id: Number;
  public customer: Customer;
  public orderDate: Date;
  public transactions: Transaction[];
  private orderTotal: Number;

}

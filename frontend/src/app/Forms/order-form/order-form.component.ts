import {Component} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {OrderService} from '../../Services/order.service';
import {ShopService} from '../../Services/shop.service';
import {Transaction} from '../../DTOs/Order/Transaction';
import {headers} from '../../Helpers/headers';
import {ItemType} from '../../Enums/ItemType';
import {AccessoryItem} from '../../DTOs/Accessory/AccessoryItem';
import {TypeCast} from '../../Pipes/TypeCast';
import {Order} from '../../DTOs/Order/Order';
import {Customer} from '../../DTOs/Order/Customer';
import {Router} from '@angular/router';

@Component({
  selector: 'order-form',
  imports: [
    FormsModule,
    ReactiveFormsModule,
    TypeCast
  ],
  templateUrl: './order-form.component.html',
  styleUrl: './order-form.component.css'
})
export class OrderFormComponent {
  orderForm: FormGroup;
  orderItems: Transaction[];
  orderTotal = 0;

  constructor(public orderService: OrderService, public formBuilder: FormBuilder, public shopService: ShopService, public router:Router) {
  }

  ngOnInit() {
    this.getOrderItems();
    this.createForm();

  }

  createForm() {
    this.orderForm = this.formBuilder.group({
      firstName: [],
      lastName: [],
      streetAddress: [],
      city: [],
      state: [],
      zip: [],
      country: [],
      email: [],
      phoneNumber: [],
    });
  }

  getOrderItems() {
    this.orderItems = this.shopService.shoppingCart;
    // @ts-ignore
    this.orderTotal = this.orderItems.reduce((a, item) => a + item.transactionPrice, 0);
  }

  submitOrder() {
    const newOrder = new Order();
    newOrder.customer = new Customer(this.orderForm.value);
    newOrder.transactions = this.orderItems;
    newOrder.orderTotal = this.orderTotal;

    this.shopService.submitOrder(newOrder)
      .subscribe(resp => {
      if(resp){
        alert("order submitted");
        this.router.navigateByUrl("/shop");
      }
    })
  }

  protected readonly headers = headers;
  protected readonly ItemType = ItemType;
  protected readonly AccessoryItem = AccessoryItem;
}

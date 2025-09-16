import { Component, computed } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { OrderService } from '../../Services/order.service';
import { ShopService } from '../../Services/shop.service';
import { Transaction } from '../../DTOs/Order/Transaction';
import { headers } from '../../Helpers/headers';
import { ItemType } from '../../Enums/ItemType';
import { AccessoryItem } from '../../DTOs/Accessory/AccessoryItem';
import { TypeCast } from '../../Pipes/TypeCast';
import { Customer } from '../../DTOs/Order/Customer';
import { Router } from '@angular/router';

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
  orderItems;
  orderTotal;

  constructor(public orderService: OrderService, public formBuilder: FormBuilder, public shopService: ShopService, public router: Router) {
    this.orderItems = computed(() => {
      return [...this.shopService.shoppingCart().values()];
    });

    this.orderTotal = computed(() => this.shopService.cartTotal());
  }

  ngOnInit() {
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


  submitOrder() {
    let customer = new Customer(this.orderForm.value);
    this.shopService.submitOrder(customer)
      .subscribe(resp => {
        if (resp) {
          alert("order submitted");
          this.router.navigateByUrl("/shop");
        }
      })
  }

  removeFromCart(trans: Transaction) {
    this.shopService.removeFromCart(trans);
  }

  goBack() {
    this.router.navigateByUrl("/shop");
  }

  protected readonly headers = headers;
  protected readonly ItemType = ItemType;
  protected readonly AccessoryItem = AccessoryItem;
}

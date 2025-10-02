import { Component, computed } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ShopService } from '../../Services/shop.service';
import { Transaction } from '../../DTOs/Order/Transaction';
import { headers } from '../../Helpers/headers';
import { ItemType } from '../../Enums/ItemType';
import { AccessoryItem } from '../../DTOs/Accessory/AccessoryItem';
import { TypeCast } from '../../Pipes/TypeCast';
import { Customer } from '../../DTOs/Order/Customer';
import { Router } from '@angular/router';
import { ToasterService } from '../../Services/toaster.service';
import { AuthService } from '../../Services/auth.service';

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
  currentCustomer: any = null;

  constructor(public formBuilder: FormBuilder, public shopService: ShopService, public router: Router, public toaster: ToasterService, public authService: AuthService) {
    this.orderItems = computed(() => {
      return [...this.shopService.shoppingCart().values()];
    });

    this.orderTotal = computed(() => this.shopService.cartTotal());
  }

  ngOnInit() {
    this.createForm();
    this.getCustomerInfo();
  }

  createForm() {
    this.orderForm = this.formBuilder.group({
      firstName: [],
      lastName: [],
      address: [],
      city: [],
      state: [],
      zipCode: [],
      country: [],
      email: [],
      phoneNumber: [],
    });
  }

  getCustomerInfo() {
    if (this.authService.user()) {
      this.authService.getCustomerInfo()
        .then(customer => {
          if (customer) {
            this.currentCustomer = JSON.parse(customer);
            for (let key of Object.keys(this.currentCustomer)) {
              if (key != null && this.orderForm.contains(key)) {
                this.orderForm.get(key).setValue(this.currentCustomer[key]);
              }
            }
          };
        });
    }
  }


  submitOrder() {
    let customer = new Customer(this.orderForm.value);
    this.shopService.submitOrder(customer)
      .then(() => this.router.navigateByUrl("/shop"));
  }

  removeFromCart(trans: Transaction) {
    this.shopService.removeFromCart(trans);
  }

  protected readonly headers = headers;
  protected readonly ItemType = ItemType;
  protected readonly AccessoryItem = AccessoryItem;
}

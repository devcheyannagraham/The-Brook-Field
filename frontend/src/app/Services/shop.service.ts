import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Order } from '../DTOs/Order/Order';
import { Transaction } from '../DTOs/Order/Transaction';
import { Customer } from '../DTOs/Order/Customer';
import { ToasterService } from './toaster.service';
import { firstValueFrom } from 'rxjs';
import {BASE_URL} from '../Helpers/globals';

@Injectable({
  providedIn: 'root'
})
export class ShopService {
  shoppingCart = signal(new Map<number, Transaction>());
  cartTotal = signal(0);


  constructor(public http: HttpClient, public toaster:ToasterService) {
  }

  addItemToCart(trans: Transaction) {
    this.shoppingCart.update(old => {
      old.set(trans.item.itemId, trans);
      this.cartTotal.update(old => old + trans.transactionPrice);
      return new Map(old);

    })
  }

  submitOrder(customer: Customer) {
    const newOrder = new Order();
    newOrder.customer = customer;
    newOrder.transactions = [...this.shoppingCart().values()];
    newOrder.orderTotal = this.cartTotal();
    this.shoppingCart.update(old => new Map<number, Transaction>())
    this.cartTotal.set(0);

    return firstValueFrom(this.http.post(`${BASE_URL}order`, newOrder))
      .then(() => this.toaster.message.set({ class: "success", message: "Order Submitted!"}))
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }))

  }

  removeFromCart(trans: Transaction | number) {
    if (trans instanceof (Transaction)) {

      this.shoppingCart.update(old => {
        old.delete(trans.item.itemId);
        this.cartTotal.update(old => old - trans.transactionPrice);

        return new Map(old);
      });
    }
    else {
      let cartItem = this.shoppingCart().get(trans);
      this.removeFromCart(cartItem);

    }
  }




}

import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Order } from '../DTOs/Order/Order';
import { Transaction } from '../DTOs/Order/Transaction';
import { Customer } from '../DTOs/Order/Customer';

@Injectable({
  providedIn: 'root'
})
export class ShopService {
  baseUrl: string = "http://localhost:8080/";
  shoppingCart = signal(new Map<number, Transaction>());
  cartTotal = signal(0);


  constructor(public http: HttpClient) {
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

    return this.http.post(`${this.baseUrl}order`, newOrder);
  }

  removeFromCart(trans: Transaction) {
    this.shoppingCart.update(old => {
      old.delete(trans.item.itemId);
      this.cartTotal.update(old => old - trans.transactionPrice);

      return new Map(old);
    });
  }


}

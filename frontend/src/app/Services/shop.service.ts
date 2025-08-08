import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Publication} from '../DTOs/Inventory/Publication';
import {Accessory} from '../DTOs/Accessory/Accessory';
import {BehaviorSubject} from 'rxjs';
import {Order} from '../DTOs/Order/Order';
import {Transaction} from '../DTOs/Order/Transaction';

@Injectable({
  providedIn: 'root'
})
export class ShopService {
  baseUrl: String = "http://localhost:8080/";
  shoppingCartSubject = new BehaviorSubject<any[]>([]); // may not use
  shoppingCart: Transaction[] = [];

  constructor(public http: HttpClient) {
  }

  addItemToCart(item: any) {
    this.shoppingCart.push(item);
    this.shoppingCartSubject.next(this.shoppingCart)
  }

  submitOrder(order: Order) {
    return this.http.post(`${this.baseUrl}order`, order);
  }

  removeFromCart(trans: Transaction) {
    const newCart = this.shoppingCart.filter(t =>
      t.item.itemId != trans.item.itemId);
    this.shoppingCartSubject.next(newCart);
    this.shoppingCart = newCart;
  }


}

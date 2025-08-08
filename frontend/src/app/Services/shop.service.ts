import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Publication} from '../DTOs/Inventory/Publication';
import {Accessory} from '../DTOs/Accessory/Accessory';
import {BehaviorSubject} from 'rxjs';
import {Order} from '../DTOs/Order/Order';

@Injectable({
  providedIn: 'root'
})
export class ShopService {
  baseUrl:String =  "http://localhost:8080/";
  shoppingCartSubject = new BehaviorSubject<any[]>([]); // may not use
  shoppingCart: any[] = [];

  constructor(public http: HttpClient) { }

  addItemToCart(item:any){
    this.shoppingCart.push(item);
    this.shoppingCartSubject.next(this.shoppingCart)
  }

  submitOrder(order:Order){
    console.log("ORDER", order);

    return this.http.post(`${this.baseUrl}order`, order);

  }




}

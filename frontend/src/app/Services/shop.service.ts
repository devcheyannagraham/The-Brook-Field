import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Publication} from '../DTOs/Inventory/Publication';
import {Accessory} from '../DTOs/Accessory/Accessory';

@Injectable({
  providedIn: 'root'
})
export class ShopService {
  baseUrl:String =  "http://localhost:8080/";
  shoppingCart: any[] = [];

  constructor(public http: HttpClient) { }

  addItemToCart(item:any){
    console.log("IMPLEMENT ADD TO CART");
    this.shoppingCart.push(item);
    console.log(this.shoppingCart);
  }


}

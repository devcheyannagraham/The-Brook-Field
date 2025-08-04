import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Publication} from '../DTOs/Inventory/Publication';
import {Accessory} from '../DTOs/Accessory/Accessory';

@Injectable({
  providedIn: 'root'
})
export class ShopService {
  baseUrl:String =  "http://localhost:8080/"

  constructor(public http: HttpClient) { }

  getPublications(){
    return this.http.get<Publication[]>(`${this.baseUrl}publications`);
  }

  getAccessories() {
    return this.http.get<Accessory[]>(`${this.baseUrl}accessories`)
  }

  addItemToCart(item:any){
    console.log("IMPLEMENT ADD TO CART");
  }


}

import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Accessory} from '../DTOs/Accessory/Accessory';

@Injectable({
  providedIn: 'root'
})
export class AccessoryService {
  baseUrl: String = 'http://localhost:8080/';

  constructor(private http:HttpClient) {
  }

  getAccessories(){
    return this.http.get<Accessory[]>(`${this.baseUrl}accessories`);
  }

  deleteAccessory(accessId:Number){
    return this.http.delete(`${this.baseUrl}accessory/${accessId}`);
  }
}

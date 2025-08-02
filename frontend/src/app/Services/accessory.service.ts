import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Accessory} from '../DTOs/Accessory/Accessory';
import {AccessoryItem} from '../DTOs/Accessory/AccessoryItem';

@Injectable({
  providedIn: 'root'
})
export class AccessoryService {
  baseUrl: String = 'http://localhost:8080/';

  constructor(private http: HttpClient) {
  }

  getAccessories() {
    return this.http.get<Accessory[]>(`${this.baseUrl}accessories`);
  }

  deleteAccessory(accessId: Number) {
    return this.http.delete(`${this.baseUrl}accessory/${accessId}`);
  }

  newAccessory(accessory: Accessory) {
    console.log("NEW ACCESSORY", accessory);
    return this.http.post(`${this.baseUrl}accessory`, accessory);
  }

  getAccessory(accessId:Number){
    return this.http.get<Accessory>(`${this.baseUrl}accessory/${accessId}`);
  }

  getAccessoryItemsByAccessoryId(accessId:Number){
    return this.http.get<AccessoryItem[]>(`${this.baseUrl}accessory/accessoryitems/${accessId}`);
  }
}

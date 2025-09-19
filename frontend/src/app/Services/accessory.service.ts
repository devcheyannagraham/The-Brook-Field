import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Accessory} from '../DTOs/Accessory/Accessory';
import {AccessoryItem} from '../DTOs/Accessory/AccessoryItem';

@Injectable({
  providedIn: 'root'
})
export class AccessoryService {
  baseUrl: string = 'http://localhost:8080/';

  constructor(private http: HttpClient) {
  }

  // READ
  getAccessories() {
    return this.http.get<Accessory[]>(`${this.baseUrl}accessories`);
  }

  getAccessory(accessId: number) {
    return this.http.get<Accessory>(`${this.baseUrl}accessory/${accessId}`);
  }

  getAvailableAccessoryItemsByAccessoryId(accessId: number) {
    return this.http.get<AccessoryItem[]>(`${this.baseUrl}accessory/accessoryitems/${accessId}`);
  }

  // DELETE

  deleteAccessory(accessId: number) {
    return this.http.delete(`${this.baseUrl}accessory/${accessId}`);
  }

  deleteAccessoryItem(accessItemId: number) {
    return this.http.delete(`${this.baseUrl}accessoryitem/${accessItemId}`);
  }

  // CREATE

  newAccessory(accessory: Accessory) {
    return this.http.post(`${this.baseUrl}accessory`, accessory);
  }

}

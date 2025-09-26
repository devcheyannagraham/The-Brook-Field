import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Accessory} from '../DTOs/Accessory/Accessory';
import {AccessoryItem} from '../DTOs/Accessory/AccessoryItem';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AccessoryService {
  baseUrl: string = 'http://localhost:8080/';

  constructor(private http: HttpClient, private authService:AuthService) {
  }

  // READ
  // Maybe used for shop
  getAccessories() {
    return this.http.get<Accessory[]>(`${this.baseUrl}accessories`);
  }

  getAccessory(accessId: number) {
    return this.http.get<Accessory>(`${this.baseUrl}accessory/${accessId}/${this.authService.user().userId}`, { withCredentials: true });
  }

  getAvailableAccessoryItemsByAccessoryId(accessId: number) {
    return this.http.get<AccessoryItem[]>(`${this.baseUrl}shop/accessory/accessoryitems/${accessId}`);
  }

  getAccessoryItemsByAccessoryId(accessId: number) {
    return this.http.get<AccessoryItem[]>(`${this.baseUrl}accessory/accessoryitems/${accessId}/${this.authService.user().userId}`, { withCredentials: true });
  }

  // DELETE

  deleteAccessory(accessId: number) {
    return this.http.delete(`${this.baseUrl}accessory/${accessId}/${this.authService.user().userId}`, { withCredentials: true });
  }

  deleteAccessoryItem(accessItemId: number) {
    return this.http.delete(`${this.baseUrl}accessoryitem/${accessItemId}/${this.authService.user().userId}`, { withCredentials: true });
  }

  // CREATE

  newAccessory(accessory: Accessory) {
    return this.http.post(`${this.baseUrl}accessory/${this.authService.user().userId}`, accessory, { withCredentials: true });
  }

}

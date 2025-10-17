import { HttpClient } from '@angular/common/http';
import { Accessory } from '../DTOs/Accessory/Accessory';
import { AccessoryItem } from '../DTOs/Accessory/AccessoryItem';
import { AuthService } from './auth.service';
import { firstValueFrom } from 'rxjs';
import { ToasterService } from './toaster.service';
import {BASE_URL} from '../Helpers/globals';
import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AccessoryService {

  constructor(private http: HttpClient, private authService: AuthService, private toaster: ToasterService) {
  }

  // READ
  getAccessories() {
    return firstValueFrom(this.http.get<Accessory[]>(`${BASE_URL}accessories`))
      .then(items => items)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

  getAccessoryById(accessId: number) {
    return firstValueFrom(this.http.get<Accessory>(`${BASE_URL}accessory/${accessId}`))
      .then(items => items)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

  getAvailableAccessoryItemsByAccessoryId(accessId: number) {
    return firstValueFrom(this.http.get<AccessoryItem[]>(`${BASE_URL}shop/accessory/accessoryitems/${accessId}`))
      .then(items => items)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

  getAccessoryItemsByAccessoryId(accessId: number) {
    return firstValueFrom(this.http.get<AccessoryItem[]>(`${BASE_URL}accessory/accessoryitems/${accessId}`, { withCredentials: true }))
      .then(items => items)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }


  // CREATE

  newAccessory(accessory: Accessory) {
    return firstValueFrom(this.http.post(`${BASE_URL}accessory`, accessory, { withCredentials: true }))
       .then(items => {
        this.toaster.message.set({ class: "success", message: "Accessory Added."})
        return items;
      })
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }


  // DELETE

  deleteAccessoryItem(accessItemId: number) {
    return firstValueFrom(this.http.delete(`${BASE_URL}accessoryitem/${accessItemId}`, { withCredentials: true }))
       .then(items => {
        this.toaster.message.set({ class: "success", message: "Accessory Item Deleted."})
        return items;
      })
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

  deleteAccessory(accessId: number) {
    return firstValueFrom(this.http.delete(`${BASE_URL}accessory/${accessId}`, { withCredentials: true }))
       .then(items => {
        this.toaster.message.set({ class: "success", message: "Accessory Deleted."})
        return items;
      })
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

}

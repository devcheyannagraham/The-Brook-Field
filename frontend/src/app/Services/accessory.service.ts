import { DestroyRef, inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Accessory } from '../DTOs/Accessory/Accessory';
import { AccessoryItem } from '../DTOs/Accessory/AccessoryItem';
import { AuthService } from './auth.service';
import { first, firstValueFrom } from 'rxjs';
import { ToasterService } from './toaster.service';

@Injectable({
  providedIn: 'root'
})
export class AccessoryService {
  baseUrl: string = 'http://localhost:8080/';
  destroyRef = inject(DestroyRef);

  constructor(private http: HttpClient, private authService: AuthService, private toaster: ToasterService) {
  }

  // READ
  getAccessories() {
    return firstValueFrom(this.http.get<Accessory[]>(`${this.baseUrl}accessories`))
      .then(items => items)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

  getAccessoryById(accessId: number) {
    return firstValueFrom(this.http.get<Accessory>(`${this.baseUrl}accessory/${accessId}`))
      .then(items => items)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

  getAvailableAccessoryItemsByAccessoryId(accessId: number) {
    return firstValueFrom(this.http.get<AccessoryItem[]>(`${this.baseUrl}shop/accessory/accessoryitems/${accessId}`))
      .then(items => items)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

  getAccessoryItemsByAccessoryId(accessId: number) {
    return firstValueFrom(this.http.get<AccessoryItem[]>(`${this.baseUrl}accessory/accessoryitems/${accessId}/${this.authService.user().userId}`, { withCredentials: true }))
      .then(items => items)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }


  // CREATE

  newAccessory(accessory: Accessory) {
    return firstValueFrom(this.http.post(`${this.baseUrl}accessory/${this.authService.user().userId}`, accessory, { withCredentials: true }))
      .then(items => items)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }


  // DELETE

  deleteAccessoryItem(accessItemId: number) {
    return firstValueFrom(this.http.delete(`${this.baseUrl}accessoryitem/${accessItemId}/${this.authService.user().userId}`, { withCredentials: true }))
      .then(items => items)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

  deleteAccessory(accessId: number) {
    return firstValueFrom(this.http.delete(`${this.baseUrl}accessory/${accessId}/${this.authService.user().userId}`, { withCredentials: true }))
      .then(items => items)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

}

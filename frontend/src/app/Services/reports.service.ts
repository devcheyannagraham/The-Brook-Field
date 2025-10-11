import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PopularItemDto } from '../DTOs/Report/PopularItemDto';
import { RecentOrderDto } from '../DTOs/Report/RecentOrderDto';
import { InventoryCountDto } from '../DTOs/Report/InventoryCountDto';
import { AuthService } from './auth.service';
import { firstValueFrom } from 'rxjs';
import { ToasterService } from './toaster.service';

@Injectable({
  providedIn: 'root'
})
export class ReportsService {
  baseUrl: string = '/api/';
  searchResults = signal<InventoryCountDto[] | null>(null);

  constructor(private http: HttpClient, private authSerivce: AuthService, public toaster: ToasterService) {
  }

  getPopularItems() {
    if (this.authSerivce.user() == null) return null;
    return firstValueFrom(this.http.get<PopularItemDto[]>(`${this.baseUrl}popularitems`, { withCredentials: true }))
      .then(items => items)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

  getShopPopularItems() {
    return firstValueFrom(this.http.get<PopularItemDto[]>(`${this.baseUrl}shop/popularitems`))
      .then(items => items)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

  getRecentOrders() {
    if (this.authSerivce.user() == null) return null;
    return firstValueFrom(this.http.get<RecentOrderDto[]>(`${this.baseUrl}recentorders`, { withCredentials: true }))
      .then(items => items)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

  getLowInventoryItems() {
    if (this.authSerivce.user() == null) return null;
    return firstValueFrom(this.http.get<InventoryCountDto[]>(`${this.baseUrl}lowinventory`, { withCredentials: true }))
      .then(items => items)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

  searchTerms(terms: string) {
    if (terms.trim() == null) return null;
    return firstValueFrom(this.http.get<InventoryCountDto[]>(`${this.baseUrl}search/${terms}`))
      .then(results => this.searchResults.set(results))
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }
}

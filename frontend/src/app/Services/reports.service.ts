import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PopularItemDto } from '../DTOs/Report/PopularItemDto';
import { RecentOrderDto } from '../DTOs/Report/RecentOrderDto';
import { InventoryCountDto } from '../DTOs/Report/InventoryCountDto';
import { AuthService } from './auth.service';
import { firstValueFrom } from 'rxjs';
import { ToasterService } from './toaster.service';
import {BASE_URL} from '../Helpers/globals';

@Injectable({
  providedIn: 'root'
})
export class ReportsService {
  searchResults = signal<InventoryCountDto[] | null>(null);

  constructor(private http: HttpClient, private authSerivce: AuthService, public toaster: ToasterService) {
  }

  getPopularItems() {
    if (this.authSerivce.user() == null) return null;
    return firstValueFrom(this.http.get<PopularItemDto[]>(`${BASE_URL}popularitems`, { withCredentials: true }))
      .then(items => items)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

  getShopPopularItems() {
    return firstValueFrom(this.http.get<PopularItemDto[]>(`${BASE_URL}shop/popularitems`))
      .then(items => items)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

  getUserRecentOrders() {
    if (this.authSerivce.user() == null) return null;
    return firstValueFrom(this.http.get<RecentOrderDto[]>(`${BASE_URL}userrecentorders`, { withCredentials: true }))
      .then(items => items)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

  getRecentOrdersReport() {
    if (this.authSerivce.user() == null) return null;
    return firstValueFrom(this.http.get<RecentOrderDto[]>(`${BASE_URL}recentordersreport`, { withCredentials: true }))
      .then(items => items)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

  getLowInventoryItems() {
    if (this.authSerivce.user() == null) return null;
    return firstValueFrom(this.http.get<InventoryCountDto[]>(`${BASE_URL}lowinventory`, { withCredentials: true }))
      .then(items => items)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

  searchTerms(terms: string) {
    if (terms.trim() == null) return null;
    return firstValueFrom(this.http.get<InventoryCountDto[]>(`${BASE_URL}search/${terms}`))
      .then(results => this.searchResults.set(results))
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }
}

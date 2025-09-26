import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PopularItemDto } from '../DTOs/Report/PopularItemDto';
import { RecentOrderDto } from '../DTOs/Report/RecentOrderDto';
import { InventoryCountDto } from '../DTOs/Report/InventoryCountDto';
import { AuthService } from './auth.service';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReportsService {
  baseUrl: string = 'http://localhost:8080/';

  constructor(private http: HttpClient, private authSerivce: AuthService) {
  }

  getPopularItems() {
    return firstValueFrom(this.http.get<PopularItemDto[]>(`${this.baseUrl}popularitems/${this.authSerivce.user().userId}`, { withCredentials: true }))
      .then(items => items)
      .catch(error => alert("POPULAR ITEMS:" + error.error));
  }

  getRecentOrders() {

    return firstValueFrom(this.http.get<RecentOrderDto[]>(`${this.baseUrl}recentorders/${this.authSerivce.user().userId}`, { withCredentials: true }))
      .then(items => items)
      .catch(error => alert("RECENT ORDERS" + error.error));
  }

  getLowInventoryItems() {
    return firstValueFrom(this.http.get<InventoryCountDto[]>(`${this.baseUrl}lowinventory/${this.authSerivce.user().userId}`, { withCredentials: true }))
      .then(items => items)
      .catch(error => alert("LOW INVENTORY:" + error.error));
  }

}

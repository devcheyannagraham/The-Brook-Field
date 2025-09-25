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
    if (this.authSerivce.isAdmin) return firstValueFrom(this.http.get<PopularItemDto[]>(`${this.baseUrl}popularitems/${this.authSerivce.user().userId}`))
      .catch(error => alert(error.error));
    else return firstValueFrom(this.http.get<PopularItemDto[]>(`${this.baseUrl}shop/popularitems`))
      .catch(error => alert(error.error));
  }

  getRecentOrders() {
    if (this.authSerivce.user() == null) return null;
    return firstValueFrom(this.http.get<RecentOrderDto[]>(`${this.baseUrl}recentorders/${this.authSerivce.user().userId}`))
      .catch(error => alert(error.error));
  }

  getLowInventoryItems() {
    if (this.authSerivce.user() == null) return null;
    if (this.authSerivce.isAdmin) return firstValueFrom(this.http.get<InventoryCountDto[]>(`${this.baseUrl}lowinventory/${this.authSerivce.user().userId}`))
      .catch(error => alert(error.error));
    return null;
  }

}

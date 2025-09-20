import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PopularItemDto } from '../DTOs/Report/PopularItemDto';
import { RecentOrderDto } from '../DTOs/Report/RecentOrderDto';
import { InventoryCountDto } from '../DTOs/Report/InventoryCountDto';
import { AuthService } from './auth.service';
import { UserRole } from '../Enums/UserRole';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReportsService {
  baseUrl: string = 'http://localhost:8080/';

  constructor(private http: HttpClient, private authSerivce: AuthService) {
  }

  getPopularItems() {
    return this.http.get<PopularItemDto[]>(`${this.baseUrl}popularitems`);
  }

  async getRecentOrders() {
    if (this.authSerivce.user() == null) return null;

    let isAdmin = await this.authSerivce.userIsAdmin();
    if (isAdmin) {
      return firstValueFrom(this.http.get<RecentOrderDto[]>(`${this.baseUrl}recentorders`));
    }
    else {
      let userId = this.authSerivce.user().userId;
      console.log(userId)
      return firstValueFrom(this.http.get<RecentOrderDto[]>(`${this.baseUrl}recentorders/${userId}`));
    }

  }

  getLowInventoryItems() {
    return this.http.get<InventoryCountDto[]>(`${this.baseUrl}lowinventory`);
  }

}

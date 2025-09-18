import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PopularItemDto } from '../DTOs/Report/PopularItemDto';
import { RecentOrderDto } from '../DTOs/Report/RecentOrderDto';
import { InventoryCountDto } from '../DTOs/Report/InventoryCountDto';

@Injectable({
  providedIn: 'root'
})
export class ReportsService {
  baseUrl: string = 'http://localhost:8080/';

  constructor(private http: HttpClient) {
  }

  getPopularItems() {
    return this.http.get<PopularItemDto[]>(`${this.baseUrl}popularitems`);
  }

  getRecentOrders(){
    return this.http.get<RecentOrderDto[]>(`${this.baseUrl}recentorders`);
  }

  getLowInventoryItems(){
    return this.http.get<InventoryCountDto[]>(`${this.baseUrl}lowinventory`);
  }


}

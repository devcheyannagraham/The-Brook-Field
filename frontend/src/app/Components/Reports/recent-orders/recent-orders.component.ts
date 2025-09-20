import { Component, DestroyRef, inject } from '@angular/core';
import { RecentOrderDto } from '../../../DTOs/Report/RecentOrderDto';
import { ReportsService } from '../../../Services/reports.service';
import { headers } from '../../../Helpers/headers';
import { CurrencyPipe } from '@angular/common';
import { RouterLink } from '@angular/router';


@Component({
  selector: 'recent-orders',
  imports: [CurrencyPipe, RouterLink],
  templateUrl: './recent-orders.component.html',
  styleUrl: './recent-orders.component.css'
})
export class RecentOrdersComponent {
  destroyRef = inject(DestroyRef);
  recentOrders: RecentOrderDto[];
  headers = headers;

  constructor(public reportService: ReportsService) { }

  ngOnInit() {
    this.getRecentOrders();
  }

  getRecentOrders() {
    this.reportService.getRecentOrders()
    .then(orders => {
      this.recentOrders = orders;
    });
  }

}

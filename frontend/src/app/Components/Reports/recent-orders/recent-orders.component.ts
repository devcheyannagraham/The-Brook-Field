import { Component, DestroyRef, inject } from '@angular/core';
import { RecentOrderDto } from '../../../DTOs/Report/RecentOrderDto';
import { ReportsService } from '../../../Services/reports.service';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { headers } from '../../../Helpers/headers';
import { CurrencyPipe } from '@angular/common';
import { BackComponent } from "../../back/back.component";


@Component({
  selector: 'recent-orders',
  imports: [CurrencyPipe, BackComponent],
  templateUrl: './recent-orders.component.html',
  styleUrl: './recent-orders.component.css'
})
export class RecentOrdersComponent {
  destroyRef = inject(DestroyRef);
  recentOrders: RecentOrderDto[];
  headers = headers;

  constructor(public reportService: ReportsService) { } 

  ngOnInit(){
    this.getRecentOrders();
  }

  getRecentOrders(){
    this.reportService.getRecentOrders()
    .pipe(takeUntilDestroyed(this.destroyRef))
    .subscribe(recentOrders => {
      this.recentOrders = recentOrders;
    })
  }


}

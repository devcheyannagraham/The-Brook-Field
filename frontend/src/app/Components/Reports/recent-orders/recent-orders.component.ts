import {Component} from '@angular/core';
import {RecentOrderDto} from '../../../DTOs/Report/RecentOrderDto';
import {ReportsService} from '../../../Services/reports.service';
import {headers} from '../../../Helpers/headers';
import {CurrencyPipe} from '@angular/common';
import {ActivatedRoute, RouterLink} from '@angular/router';
import {AuthService} from '../../../Services/auth.service';
import {SVGIconComponent} from "../../svgicon/svgicon.component";


@Component({
  selector: 'recent-orders',
  imports: [CurrencyPipe, RouterLink, SVGIconComponent],
  templateUrl: './recent-orders.component.html',
  styleUrl: './recent-orders.component.css'
})
export class RecentOrdersComponent {
  recentOrders: RecentOrderDto[] | void;
  headers = headers;

  constructor(public reportService: ReportsService, public authService: AuthService, public route: ActivatedRoute) {
  }

  ngOnInit() {
    this.getRecentOrders();
  }

  getRecentOrders() {
    if (this.route.snapshot.data["report"] == true) {
      this.reportService.getRecentOrdersReport()
        .then(orders => {
          this.recentOrders = orders;
        });
    } else {
      this.reportService.getUserRecentOrders()
        .then(orders => {
          this.recentOrders = orders;
        });
    }
  }

}

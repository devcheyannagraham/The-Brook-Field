import { Component } from '@angular/core';
import { RecentOrdersComponent } from "../../Reports/recent-orders/recent-orders.component";
import { RouterLink } from '@angular/router';
import { PopularItemsComponent } from "../../Reports/popular-items/popular-items.component";


@Component({
  selector: 'home',
  imports: [RecentOrdersComponent, RouterLink, PopularItemsComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}

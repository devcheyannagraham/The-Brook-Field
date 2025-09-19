import { Component } from '@angular/core';
import { PopularItemsComponent } from "../../Reports/popular-items/popular-items.component";
import { RecentOrdersComponent } from "../../Reports/recent-orders/recent-orders.component";
import { RouterLink } from '@angular/router';


@Component({
  selector: 'home',
  imports: [PopularItemsComponent, RecentOrdersComponent, RouterLink],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}

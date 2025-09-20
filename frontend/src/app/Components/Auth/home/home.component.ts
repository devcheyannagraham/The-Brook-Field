import { Component } from '@angular/core';
import { RecentOrdersComponent } from "../../Reports/recent-orders/recent-orders.component";
import { RouterLink } from '@angular/router';


@Component({
  selector: 'home',
  imports: [RecentOrdersComponent, RouterLink],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}

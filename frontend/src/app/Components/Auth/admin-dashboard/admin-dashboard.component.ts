import { Component } from '@angular/core';
import { PublicationsComponent } from '../../Products/publications/publications.component';
import { AccessoriesComponent } from '../../Products/accessories/accessories.component';
import { RouterLink } from '@angular/router';
import { BackComponent } from "../../back/back.component";
@Component({
  selector: 'admin-dashboard',
  imports: [PublicationsComponent, AccessoriesComponent, RouterLink, BackComponent],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css'
})
export class AdminDashboardComponent {

}

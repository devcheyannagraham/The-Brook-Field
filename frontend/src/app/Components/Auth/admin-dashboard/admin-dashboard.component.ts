import { Component } from '@angular/core';
import { PublicationsComponent } from '../../Products/publications/publications.component';
import { AccessoriesComponent } from '../../Products/accessories/accessories.component';
import { RouterLink } from '@angular/router';
@Component({
  selector: 'admin-dashboard',
  imports: [PublicationsComponent, AccessoriesComponent, RouterLink],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css'
})
export class AdminDashboardComponent {

}

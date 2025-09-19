import { Component } from '@angular/core';
import { PopularItemsComponent } from "../Reports/popular-items/popular-items.component";
import { LoginComponent } from "../Auth/login/login.component";
import { RouterLink } from '@angular/router';


@Component({
  selector: 'landing-page',
  imports: [PopularItemsComponent, LoginComponent, RouterLink],
  templateUrl: './landing-page.component.html',
  styleUrl: './landing-page.component.css'
})
export class LandingPageComponent {

}

import { Component } from '@angular/core';
import { PopularItemsComponent } from "../Reports/popular-items/popular-items.component";


@Component({
  selector: 'landing-page',
  imports: [PopularItemsComponent],
  templateUrl: './landing-page.component.html',
  styleUrl: './landing-page.component.css'
})
export class LandingPageComponent {

}

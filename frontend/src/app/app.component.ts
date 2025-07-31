import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { PublicationsComponent } from './Components/publications/publications.component';
import {PublicationItemsComponent} from './Components/publication-items/publication-items.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, PublicationsComponent, PublicationItemsComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'frontend';
}

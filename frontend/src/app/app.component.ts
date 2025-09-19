import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { PublicationsComponent } from './Components/Products/publications/publications.component';
import {PublicationItemsComponent} from './Components/Products/publication-items/publication-items.component';
import {PublicationItemFormComponent} from './Forms/publication-item-form/publication-item-form.component';
import {PublicationFormComponent} from './Forms/publication-form/publication-form.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, PublicationItemFormComponent, PublicationFormComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'frontend';
}

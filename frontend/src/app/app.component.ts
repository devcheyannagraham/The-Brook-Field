import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { PublicationComponent } from './Components/publication/publication.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, PublicationComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'frontend';
}

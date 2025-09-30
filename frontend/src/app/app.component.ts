import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { AuthService } from './Services/auth.service';
import { BackComponent } from "./Components/back/back.component";
import { ToasterComponent } from "./Components/toaster/toaster.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink, BackComponent, ToasterComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  window = window;
  constructor(public authService: AuthService) {
  }

  logout(){
    this.authService.logout();
  }
}

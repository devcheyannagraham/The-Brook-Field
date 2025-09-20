import { Component } from '@angular/core';
import {  RouterLink, RouterOutlet } from '@angular/router';
import { AuthService } from './Services/auth.service';
import { BackComponent } from "./Components/back/back.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink, BackComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'frontend';
  constructor(public authService: AuthService) {
  }

  logout(){
    this.authService.logout();
  }
}

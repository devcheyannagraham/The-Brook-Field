import {Component, OnInit} from '@angular/core';
import {PopularItemsComponent} from "../Reports/popular-items/popular-items.component";
import {ToasterService} from '../../Services/toaster.service';
import {ActivatedRoute} from '@angular/router';


@Component({
  selector: 'landing-page',
  imports: [PopularItemsComponent],
  templateUrl: './landing-page.component.html',
  styleUrl: './landing-page.component.css'
})
export class LandingPageComponent implements OnInit {
  constructor(public toaster: ToasterService, public activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(params => {
      if (params["redirected"]) this.toaster.message.set({class: 'error', message: '404 - Not Found'});
    })
  }
}

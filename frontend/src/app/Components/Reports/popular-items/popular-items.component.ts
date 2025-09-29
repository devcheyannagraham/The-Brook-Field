import { Component, DestroyRef, inject } from '@angular/core';
import { ReportsService } from '../../../Services/reports.service';
import { PopularItemDto } from '../../../DTOs/Report/PopularItemDto';
import { CurrencyPipe } from '@angular/common';
import { headers } from '../../../Helpers/headers';
import { ItemType } from '../../../Enums/ItemType';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../../Services/auth.service';

@Component({
  selector: 'popular-items',
  imports: [CurrencyPipe, RouterLink],
  templateUrl: './popular-items.component.html',
  styleUrl: './popular-items.component.css'
})
export class PopularItemsComponent {
  destroyRef = inject(DestroyRef);
  popularItems: PopularItemDto[] | void;
  headers = headers;
  ItemType = ItemType;
  isAdmin: void | boolean = false;

  constructor(public reportService: ReportsService, public authService: AuthService) {
  }

  ngOnInit() {
    this.getPopularItems();

  }

  getPopularItems() {
    if (this.authService.user() != null) {
      this.authService.getUserRole()
        .then(isAdmin => {
          if (isAdmin) {
            this.isAdmin = true;
            this.reportService.getPopularItems()
              .then(items => this.popularItems = items);
          } else {
            this.isAdmin = false;
            this.reportService.getShopPopularItems()
              .then(items => this.popularItems = items);
          }
        });
    }
    else {
      this.isAdmin = false;
      this.reportService.getShopPopularItems()
        .then(items => {console.log(items);this.popularItems = items});

    }

  }
}

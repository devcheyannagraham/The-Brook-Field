import { Component, DestroyRef, inject } from '@angular/core';
import { ReportsService } from '../../../Services/reports.service';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
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
  popularItems:PopularItemDto[];
  headers = headers;
  ItemType = ItemType;
  isAdmin = false;

  constructor(public reportService: ReportsService, public authService:AuthService) { 
    this.authService.userIsAdmin().then(result =>this.isAdmin = result);
  }

  ngOnInit() {
    this.getPopularItems();

  }

  getPopularItems() {
    this.reportService.getPopularItems()
    .pipe(takeUntilDestroyed(this.destroyRef))
    .subscribe(popularItems => {
      this.popularItems = popularItems;
    });

  }

}

import { Component, DestroyRef, inject } from '@angular/core';
import { ReportsService } from '../../../Services/reports.service';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { PopularItemDto } from '../../../DTOs/Report/PopularItemDto';
import { CurrencyPipe } from '@angular/common';
import { headers } from '../../../Helpers/headers';
import { BackComponent } from "../../back/back.component";
import { ItemType } from '../../../Enums/ItemType';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'popular-items',
  imports: [CurrencyPipe, BackComponent, RouterLink],
  templateUrl: './popular-items.component.html',
  styleUrl: './popular-items.component.css'
})
export class PopularItemsComponent {
  destroyRef = inject(DestroyRef);
  popularItems:PopularItemDto[];
  headers = headers;
  ItemType = ItemType;

  constructor(public reportService: ReportsService) { }

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

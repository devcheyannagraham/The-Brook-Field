import { Component, DestroyRef, inject } from '@angular/core';
import { ReportsService } from '../../../Services/reports.service';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { PopularItemDto } from '../../../DTOs/Report/PopularItemDto';
import { CurrencyPipe } from '@angular/common';
import { headers } from '../../../Helpers/headers';

@Component({
  selector: 'popular-items',
  imports: [CurrencyPipe],
  templateUrl: './popular-items.component.html',
  styleUrl: './popular-items.component.css'
})
export class PopularItemsComponent {
  destroyRef = inject(DestroyRef);
  popularItems:PopularItemDto[];
  headers = headers;

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

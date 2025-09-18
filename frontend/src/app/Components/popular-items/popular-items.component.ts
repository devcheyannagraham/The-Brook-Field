import { Component, DestroyRef, inject } from '@angular/core';
import { ReportsService } from '../../Services/reports.service';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { PopularItemDto } from '../../DTOs/Report/PopularItemDto';

@Component({
  selector: 'popular-items',
  imports: [],
  templateUrl: './popular-items.component.html',
  styleUrl: './popular-items.component.css'
})
export class PopularItemsComponent {
  destroyRef = inject(DestroyRef);
  popularItems:PopularItemDto[];
  constructor(public reportService: ReportsService) { }

  ngOnInit() {
    this.getPopularItems();
  }

  getPopularItems() {
    this.reportService.getPopularItems()
    .pipe(takeUntilDestroyed(this.destroyRef))
    .subscribe(popularItems => {
      console
      this.popularItems = popularItems;
    });

  }

}

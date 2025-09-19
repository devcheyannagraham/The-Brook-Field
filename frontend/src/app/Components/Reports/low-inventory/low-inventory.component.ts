import { Component,inject, DestroyRef } from '@angular/core';
import { InventoryCountDto } from '../../../DTOs/Report/InventoryCountDto';
import { headers } from '../../../Helpers/headers';
import { ReportsService } from '../../../Services/reports.service';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { BackComponent } from "../../back/back.component";


@Component({
  selector: 'low-inventory',
  imports: [BackComponent],
  templateUrl: './low-inventory.component.html',
  styleUrl: './low-inventory.component.css'
})
export class LowInventoryComponent {

  destroyRef = inject(DestroyRef);
  lowInventoryItems:InventoryCountDto[];
  headers = headers;

  constructor(public reportService: ReportsService) { } 

  ngOnInit(){
    this.getLowInventoryItems();
  }

  getLowInventoryItems(){
    this.reportService.getLowInventoryItems()
    .pipe(takeUntilDestroyed(this.destroyRef))
    .subscribe(lowInventoryItems => {
      this.lowInventoryItems = lowInventoryItems;
    })
  }


}

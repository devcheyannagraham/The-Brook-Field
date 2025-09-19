import { Component,inject, DestroyRef } from '@angular/core';
import { InventoryCountDto } from '../../../DTOs/Report/InventoryCountDto';
import { headers } from '../../../Helpers/headers';
import { ReportsService } from '../../../Services/reports.service';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { BackComponent } from "../../back/back.component";
import { RouterLink } from '@angular/router';
import { ItemType } from '../../../Enums/ItemType';


@Component({
  selector: 'low-inventory',
  imports: [BackComponent, RouterLink],
  templateUrl: './low-inventory.component.html',
  styleUrl: './low-inventory.component.css'
})
export class LowInventoryComponent {

  destroyRef = inject(DestroyRef);
  lowInventoryItems:InventoryCountDto[];
  headers = headers;
  ItemType = ItemType;

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

import { Component,inject, DestroyRef } from '@angular/core';
import { InventoryCountDto } from '../../../DTOs/Report/InventoryCountDto';
import { headers } from '../../../Helpers/headers';
import { ReportsService } from '../../../Services/reports.service';
import { RouterLink } from '@angular/router';
import { ItemType } from '../../../Enums/ItemType';
import { SVGIconComponent } from "../../svgicon/svgicon.component";

@Component({
  selector: 'low-inventory',
  imports: [RouterLink, SVGIconComponent],
  templateUrl: './low-inventory.component.html',
  styleUrl: './low-inventory.component.css'
})
export class LowInventoryComponent {

  destroyRef = inject(DestroyRef);
  lowInventoryItems:InventoryCountDto[] | void;
  headers = headers;
  ItemType = ItemType;

  constructor(public reportService: ReportsService) { } 

  ngOnInit(){
    this.getLowInventoryItems();
  }

  getLowInventoryItems(){
    this.reportService.getLowInventoryItems()
    .then(lowInventoryItems => {
      this.lowInventoryItems = lowInventoryItems;
    })
  }


}

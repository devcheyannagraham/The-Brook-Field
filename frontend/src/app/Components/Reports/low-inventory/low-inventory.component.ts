import { Component } from '@angular/core';
import { InventoryCountDto } from '../../../DTOs/Report/InventoryCountDto';
import { headers } from '../../../Helpers/headers';
import { ReportsService } from '../../../Services/reports.service';
import { RouterLink } from '@angular/router';
import { ItemType } from '../../../Enums/ItemType';
import { SVGIconComponent } from "../../svgicon/svgicon.component";
import {DatePipe} from '@angular/common';

@Component({
  selector: 'low-inventory',
  imports: [RouterLink, SVGIconComponent, DatePipe],
  templateUrl: './low-inventory.component.html',
  styleUrl: './low-inventory.component.css'
})
export class LowInventoryComponent {

  lowInventoryItems:InventoryCountDto[] | void;
  headers = headers;
  ItemType = ItemType;
  date = new Date();

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


  protected readonly Object = Object;
}

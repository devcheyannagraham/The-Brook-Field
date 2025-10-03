import { Component, Input } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { Accessory } from '../../../DTOs/Accessory/Accessory';
import { AccessoryItem } from '../../../DTOs/Accessory/AccessoryItem';
import { AccessoryService } from '../../../Services/accessory.service';
import { headers } from '../../../Helpers/headers';
import { SVGIconComponent } from "../../svgicon/svgicon.component";
import { CurrencyPipe } from '@angular/common';

@Component({
  selector: 'accessory-items',
  imports: [
    RouterLink,
    SVGIconComponent,
    CurrencyPipe
],
  templateUrl: './accessory-items.component.html',
  styleUrl: './accessory-items.component.css'
})
export class AccessoryItemsComponent {
  @Input() accessId: number;
  accessory: Accessory;
  accessoryItems: AccessoryItem[];

  constructor(public accessoryService: AccessoryService, public router: Router) {
  }

  ngOnInit() {
    this.getAccessoryData();
  }

  getAccessoryData() {
    if (this.accessId) {
      this.accessoryService.getAccessoryById(this.accessId)
        .then(acc => {
          if (acc) {
            this.accessory = acc;
            this.getAccessoryItems();
          }
        });
    }
  }

  getAccessoryItems() {
    if (this.accessId) {
      this.accessoryService.getAccessoryItemsByAccessoryId(this.accessId)
        .then(data => {
          if (data) this.accessoryItems = data;
        });
    }
  }

  deleteAccessory(accessId: number) {
    this.accessoryService.deleteAccessory(accessId)
      .then(result => {
        if (result) this.router.navigateByUrl("/accessories");
      })
  }

  deleteAccessoryItem(accessItemId: number) {
    if (accessItemId) {
      this.accessoryService.deleteAccessoryItem(accessItemId)
        .then(result => {
          if (result) this.getAccessoryItems();
        });
    }
  }

  protected readonly headers = headers;
}

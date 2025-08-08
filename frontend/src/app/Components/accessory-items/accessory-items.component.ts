import {Component, Input} from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {Accessory} from '../../DTOs/Accessory/Accessory';
import {AccessoryItem} from '../../DTOs/Accessory/AccessoryItem';
import {AccessoryService} from '../../Services/accessory.service';
import {headers} from '../../Helpers/headers';

@Component({
  selector: 'accessory-items',
  imports: [
    RouterLink
  ],
  templateUrl: './accessory-items.component.html',
  styleUrl: './accessory-items.component.css'
})
export class AccessoryItemsComponent {
  @Input() accessId: Number;
  accessory: Accessory;
  accessoryItems: AccessoryItem[];

  constructor(public accessoryService: AccessoryService, public router: Router) {
  }

  ngOnInit() {
    this.getAccessoryData();
  }

  getAccessoryData() {
    if (this.accessId) {
      this.accessoryService.getAccessory(this.accessId)
        .subscribe(acc => {
          this.accessory = acc;
          this.getAccessoryItems();
        });
    }
  }

  getAccessoryItems() {
    if (this.accessId) {
      this.accessoryService.getAvailableAccessoryItemsByAccessoryId(this.accessId)
        .subscribe(data => {
          this.accessoryItems = data;
        });
    }
  }

  deleteAccessory(accessId: Number) {
    this.accessoryService.deleteAccessory(accessId)
      .subscribe(result => {
        if (result) {
          this.router.navigateByUrl("/accessories");
        }
      })
  }

  deleteAccessoryItem(accessItemId: Number) {
    if (accessItemId) {
      this.accessoryService.deleteAccessoryItem(accessItemId)
        .subscribe(result => {
          if (result) {
            this.getAccessoryItems();
          }
        });
    }
  }

  protected readonly headers = headers;
}

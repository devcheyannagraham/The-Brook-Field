import {Component, Input} from '@angular/core';
import {DatePipe} from '@angular/common';
import {RouterLink} from '@angular/router';
import {Accessory} from '../../DTOs/Accessory/Accessory';
import {AccessoryItem} from '../../DTOs/Accessory/AccessoryItem';
import {AccessoryService} from '../../Services/accessory.service';

@Component({
  selector: 'accessory-items',
  imports: [
    DatePipe,
    RouterLink
  ],
  templateUrl: './accessory-items.component.html',
  styleUrl: './accessory-items.component.css'
})
export class AccessoryItemsComponent {
  @Input() accessId: Number;
  accessory: Accessory;
  accessoryItems: AccessoryItem[];

  constructor(public accessoryService: AccessoryService) {
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

  getAccessoryItems(){
    if(this.accessId){
      this.accessoryService.getAccessoryItemsByAccessoryId(this.accessId)
        .subscribe(data => {
          this.accessoryItems = data;
          console.log(data)
        });
    }
  }

}

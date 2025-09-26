import { Component } from '@angular/core';
import { AccessoryService } from '../../../Services/accessory.service';
import { Accessory } from '../../../DTOs/Accessory/Accessory';
import { headers } from '../../../Helpers/headers';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'accessories',
  imports: [
    RouterLink
  ],
  templateUrl: './accessories.component.html',
  styleUrl: './accessories.component.css'
})
export class AccessoriesComponent {
  accessories: Accessory[];


  constructor(public accessoryService: AccessoryService) {
  }

  ngOnInit() {
    this.getAccessories();
  }

  getAccessories() {
    this.accessoryService.getAccessories()
      .then(data => {
        if (data) this.accessories = data;
      });
  }

  protected readonly headers = headers;
}

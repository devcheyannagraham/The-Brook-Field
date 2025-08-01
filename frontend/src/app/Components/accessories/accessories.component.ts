import {Component} from '@angular/core';
import {AccessoryService} from '../../Services/accessory.service';
import {Accessory} from '../../DTOs/Accessory/Accessory';
import {headers} from '../../Helpers/headers';
import {RouterLink} from '@angular/router';

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
      .subscribe(data => {
        this.accessories = data;
        console.log(this.accessories);
      });
  }

  deleteAccessory(accessId:Number){
    this.accessoryService.deleteAccessory(accessId)
      .subscribe(result => {
        if(result){
          this.getAccessories();
        }
      })
  }


  protected readonly headers = headers;
}

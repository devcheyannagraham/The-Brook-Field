import {Component} from '@angular/core';
import {AccessoryType} from '../../Enums/AccessoryType';
import {ShopService} from '../../Services/shop.service';
import {Publication} from '../../DTOs/Inventory/Publication';
import {Accessory} from '../../DTOs/Accessory/Accessory';
import {DatePipe} from '@angular/common';
import {headers} from '../../Helpers/headers';
import {RouterLink} from '@angular/router';
import {AccessoryService} from '../../Services/accessory.service';
import {PublicationService} from '../../Services/publication.service';
import {Purchase} from '../../DTOs/Order/Purchase';

@Component({
  selector: 'shop',
  imports: [
    DatePipe,
    RouterLink,
  ],
  templateUrl: './shop.component.html',
  styleUrl: './shop.component.css'
})
export class ShopComponent {
  mugs: Accessory[] = [];
  pens: Accessory[] = [];
  bookmarks: Accessory[] = [];
  protected readonly headers = headers;


  publications: Publication[];

  constructor(public shopService: ShopService, public accessoryService: AccessoryService, public publicationService:PublicationService) {
  }

  ngOnInit() {
    this.getPublications();
    this.getAccessories();
  }

  getPublications() {
    this.publicationService.getPublications()
      .subscribe(data => {
        console.log(data)
        this.publications = data;
      });
  }

  getAccessories() {
    this.accessoryService.getAccessories()
      .subscribe(data => {
        console.log(data);

        for (let acc of data) {
          if (acc.accessoryType == AccessoryType.PEN) {
            this.pens.push(acc);
          }
          if (acc.accessoryType == AccessoryType.BOOKMARK) {
            this.bookmarks.push(acc);
          }
          if (acc.accessoryType == AccessoryType.MUG) {
            this.mugs.push(acc);
          }
        }
      });
  }

  addItemToCart(item: any){
    const purchase = new Purchase();
    purchase.item = item;
    purchase.transactionPrice = item.price;
    this.shopService.addItemToCart(purchase);
  }

}

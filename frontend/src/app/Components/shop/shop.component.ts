import {Component} from '@angular/core';
import {LiteraryPiece} from '../../DTOs/Inventory/LiteraryPiece';
import {AccessoryItem} from '../../DTOs/Accessory/AccessoryItem';
import {Journal} from '../../DTOs/Inventory/Journal';
import {Book} from '../../DTOs/Inventory/Book';
import {ItemType} from '../../Enums/ItemType';
import {PublicationItem} from '../../DTOs/Inventory/PublicationItem';
import {AccessoryType} from '../../Enums/AccessoryType';
import {PublicationItemType} from '../../Enums/PublicationItemType';
import {ShopService} from '../../Services/shop.service';
import {Publication} from '../../DTOs/Inventory/Publication';
import {Accessory} from '../../DTOs/Accessory/Accessory';
import {DatePipe, KeyValuePipe} from '@angular/common';
import {headers} from '../../Helpers/headers';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'shop',
  imports: [
    DatePipe,
    RouterLink,
    KeyValuePipe
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

  constructor(public shopService: ShopService) {
  }

  ngOnInit() {
    this.getPublications();
    this.getAccessories();
  }

  getPublications() {
    this.shopService.getPublications()
      .subscribe(data => {
        console.log(data)
        this.publications = data;


      });
  }

  getAccessories() {
    this.shopService.getAccessories()
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
    this.shopService.addItemToCart(item);
  }

}

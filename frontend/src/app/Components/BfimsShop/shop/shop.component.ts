import { Component } from '@angular/core';
import { AccessoryType } from '../../../Enums/AccessoryType';
import { Publication } from '../../../DTOs/Inventory/Publication';
import { Accessory } from '../../../DTOs/Accessory/Accessory';
import { DatePipe } from '@angular/common';
import { headers } from '../../../Helpers/headers';
import { RouterLink } from '@angular/router';
import { AccessoryService } from '../../../Services/accessory.service';
import { PublicationService } from '../../../Services/publication.service';
import { SearchComponent } from '../../search/search.component';
import { InventoryCountDto } from '../../../DTOs/Report/InventoryCountDto';
import { ItemType } from '../../../Enums/ItemType';

@Component({
  selector: 'shop',
  imports: [
    DatePipe,
    RouterLink,
    SearchComponent
  ],
  templateUrl: './shop.component.html',
  styleUrl: './shop.component.css'
})
export class ShopComponent {
  mugs: Accessory[] = [];
  pens: Accessory[] = [];
  bookmarks: Accessory[] = [];
  protected readonly headers = headers;
  searchData: InventoryCountDto[];
  itemType = ItemType;

  publications: Publication[];

  constructor(public accessoryService: AccessoryService, public publicationService: PublicationService) {
  }

  ngOnInit() {
    this.getPublications();
    this.getAccessories();
  }

  getPublications() {
    this.publicationService.getPublications()
      .then(data => {
        if (data) this.publications = data;
      });
  }

  getAccessories() {
    this.accessoryService.getAccessories()
      .then(data => {
        if (data) {
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
        }
      });
  }

  showSearchItems(data:InventoryCountDto[]){
    this.searchData = data;
  }

  closeSearch(){
    this.searchData = null;
  }
}

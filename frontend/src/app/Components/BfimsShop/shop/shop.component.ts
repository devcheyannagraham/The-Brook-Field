import {Component} from '@angular/core';
import {AccessoryType} from '../../../Enums/AccessoryType';
import {Publication} from '../../../DTOs/Inventory/Publication';
import {Accessory} from '../../../DTOs/Accessory/Accessory';
import {CurrencyPipe, DatePipe} from '@angular/common';
import {headers} from '../../../Helpers/headers';
import {RouterLink} from '@angular/router';
import {AccessoryService} from '../../../Services/accessory.service';
import {PublicationService} from '../../../Services/publication.service';
import {SearchComponent} from '../../search/search.component';
import {ItemType} from '../../../Enums/ItemType';
import {ReportsService} from '../../../Services/reports.service';
import {CartComponent} from "../cart/cart.component";
import {SVGIconComponent} from "../../svgicon/svgicon.component";

@Component({
  selector: 'shop',
  imports: [
    DatePipe,
    RouterLink,
    SearchComponent,
    CartComponent,
    SVGIconComponent,
    CurrencyPipe
  ],
  templateUrl: './shop.component.html',
  styleUrl: './shop.component.css'
})
export class ShopComponent {
  accessoryData: Accessory[];
  protected readonly headers = headers;
  publications: Publication[];

  constructor(public accessoryService: AccessoryService, public publicationService: PublicationService, public reportService: ReportsService) {
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
          this.accessoryData = data;
        }
      });
  }

  closeSearch() {
    this.reportService.searchResults.set(null);
  }

  protected readonly Object = Object;
  protected readonly AccessoryType = AccessoryType;
  protected readonly ItemType = ItemType;
}

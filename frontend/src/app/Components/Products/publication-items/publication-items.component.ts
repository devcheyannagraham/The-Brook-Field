import {Component, Input} from '@angular/core';
import {PublicationService} from '../../../Services/publication.service';
import {PublicationItem} from '../../../DTOs/Inventory/PublicationItem';
import {Publication} from '../../../DTOs/Inventory/Publication';
import {Journal} from '../../../DTOs/Inventory/Journal';
import {PublicationItemType} from '../../../Enums/PublicationItemType';
import {headers} from '../../../Helpers/headers';
import {DatePipe} from '@angular/common';
import {Router, RouterLink} from '@angular/router';
import {SVGIconComponent} from '../../svgicon/svgicon.component';
import {
  ITEM_TYPE_TABLE_HEADERS_REPORT,
  ITEM_TYPE_TABLE_HEADERS_SHOP,
  PUBLICATION_ITEM_TYPE_TABLE_HEADERS_REPORT, PUBLICATION_ITEM_TYPE_TABLE_HEADERS_SHOP
} from '../../../Helpers/globals';
import {ItemType} from '../../../Enums/ItemType';

@Component({
  selector: 'publication-items',
  imports: [
    DatePipe,
    RouterLink,
    SVGIconComponent,
  ],
  templateUrl: './publication-items.component.html',
  styleUrl: './publication-items.component.css'
})
export class PublicationItemsComponent {
  publication: Publication;
  publicationItems: PublicationItem[];
  filteredPublicationItems = new Map<PublicationItemType, PublicationItem[]>();

  @Input() publicationId: number;


  //For Template
  PublicationItemType = PublicationItemType;
  Journal = Journal;
  headers = headers;

  constructor(public pubService: PublicationService, public router: Router) {
  }

  ngOnInit() {
    this.getPublicationData();
  }

  getPublicationData() {
    if (this.publicationId) {
      this.pubService.getPublicationById(this.publicationId)
        .then(pub => {
          if (pub) {
            this.publication = pub;
            this.getPublicationItemsByPublicationId();
          }
        });
    }
  }

  getPublicationItemsByPublicationId() {
    if (this.publicationId) {
      this.pubService.getPublicationItemsByPublicationId(this.publication.publicationId)
        .then(pubItems => {
          if (pubItems) {
            this.publicationItems = pubItems;
            this.filterPublicationItems();
            this.filterPublicationItems();
          }
        });
    }
  }

  filterPublicationItems() {
    for (let itemType of Object.values(PublicationItemType)) {
      this.filteredPublicationItems.set(itemType, []);
    }
    for (let item of this.publicationItems.values()) {
      let itemGroup = this.filteredPublicationItems.get(item.publicationItemType);
      itemGroup.push(item);
    }
  }

  deleteItem(itemId: number) {
    this.pubService.deletePublicationItem(itemId)
      .then(result => {
        if (result) {
          //update items
          this.getPublicationItemsByPublicationId();
        }
      });
  }

  deletePublication(pubId: number) {
    this.pubService.deletePublication(pubId)
      .then(result => {
        if (result) {
          this.router.navigateByUrl("/publications");
        }
      })
  }

  protected readonly ITEM_TYPE_TABLE_HEADERS_SHOP = ITEM_TYPE_TABLE_HEADERS_SHOP;
  protected readonly ItemType = ItemType;
  protected readonly PUBLICATION_ITEM_TYPE_TABLE_HEADERS_SHOP = PUBLICATION_ITEM_TYPE_TABLE_HEADERS_SHOP;
  protected readonly Object = Object;
  protected readonly PUBLICATION_ITEM_TYPE_TABLE_HEADERS_REPORT = PUBLICATION_ITEM_TYPE_TABLE_HEADERS_REPORT;
  protected readonly ITEM_TYPE_TABLE_HEADERS_REPORT = ITEM_TYPE_TABLE_HEADERS_REPORT;
}

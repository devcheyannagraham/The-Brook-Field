import {Component, Input} from '@angular/core';
import {DatePipe} from "@angular/common";
import {ShopService} from '../../Services/shop.service';
import {AccessoryService} from '../../Services/accessory.service';
import {PublicationService} from '../../Services/publication.service';
import {Book} from '../../DTOs/Inventory/Book';
import {LiteraryPiece} from '../../DTOs/Inventory/LiteraryPiece';
import {PublicationItem} from '../../DTOs/Inventory/PublicationItem';
import {Publication} from '../../DTOs/Inventory/Publication';
import {Journal} from '../../DTOs/Inventory/Journal';
import {PublicationItemType} from '../../Enums/PublicationItemType';
import {Location} from '@angular/common';
import {headers} from '../../Helpers/headers';


@Component({
  selector: 'shop-item-detail',
  imports: [
    DatePipe
  ],
  templateUrl: './shop-item-detail.component.html',
  styleUrl: './shop-item-detail.component.css'
})
export class ShopItemDetailComponent {
  publication: Publication;
  publicationItems: PublicationItem[];
  books: Book[];
  journals: Journal[];
  literaryPieces: LiteraryPiece[]
  @Input() shopItemId: Number;

  constructor(public shopService: ShopService, public accessoryService: AccessoryService, public publicationService: PublicationService, public location: Location) {
  }

  ngOnInit() {
    this.getPublicationData();
  }

  getPublicationData() {
    if (this.shopItemId) {
      this.publicationService.getPublicationById(this.shopItemId)
        .subscribe(pub => {
          this.publication = pub;
          this.getPublicationItemsByPublicationId();
        });
    }
  }

  getPublicationItemsByPublicationId() {
    if (this.shopItemId) {
      this.publicationService.getPublicationItemsByPublicationId(this.publication.publicationId)
        .subscribe(pubItems => {
          if (pubItems) {
            this.publicationItems = pubItems;
            this.filterPublicationItems();
          }
        });
    }
  }

  filterPublicationItems() {
    this.books = this.publicationItems
      .filter(item => item.publicationItemType === PublicationItemType.BOOK)
      .map(item => item as Book);
    this.journals = this.publicationItems
      .filter(item => item.publicationItemType === PublicationItemType.JOURNAL)
      .map(item => item as Journal);
    this.literaryPieces = this.publicationItems
      .filter(item => item.publicationItemType === PublicationItemType.LITERARY_PIECE)
      .map(item => item as LiteraryPiece);

  }

  purchaseItem(tem: PublicationItem) {
    console.log("IMPLEMENT PURCHASE DTO")
  }

  rentItem(tem: PublicationItem) {
    console.log("IMPLEMENT rentalE DTO")

  }

  addItemToCart(item: any) {
    // this.shopService.addItemToCart(item)
    console.log("IMPLEMENT ADD ITEM TO CART")
  }

  goBack() {
    this.location.back();
  }


  protected readonly headers = headers;
}

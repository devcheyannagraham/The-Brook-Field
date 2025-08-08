import {Component, Input} from '@angular/core';
import {DatePipe, Location} from "@angular/common";
import {ShopService} from '../../Services/shop.service';
import {AccessoryService} from '../../Services/accessory.service';
import {PublicationService} from '../../Services/publication.service';
import {Book} from '../../DTOs/Inventory/Book';
import {LiteraryPiece} from '../../DTOs/Inventory/LiteraryPiece';
import {PublicationItem} from '../../DTOs/Inventory/PublicationItem';
import {Publication} from '../../DTOs/Inventory/Publication';
import {Journal} from '../../DTOs/Inventory/Journal';
import {PublicationItemType} from '../../Enums/PublicationItemType';
import {headers} from '../../Helpers/headers';
import {Rental} from '../../DTOs/Order/Rental';
import {Purchase} from '../../DTOs/Order/Purchase';
import {Router, RouterLink} from '@angular/router';


@Component({
  selector: 'shop-item-detail',
  imports: [
    DatePipe,
    RouterLink
  ],
  templateUrl: './shop-publication-detail.component.html',
  styleUrl: './shop-publication-detail.component.css'
})
export class ShopPublicationDetailComponent {
  publication: Publication;
  publicationItems: any[];
  books: Book[];
  journals: Journal[];
  literaryPieces: LiteraryPiece[]
  @Input() shopItemId: Number;

  constructor(public shopService: ShopService, public publicationService: PublicationService, public router: Router) {
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

  purchaseItem(item: PublicationItem) {
    const purchase = new Purchase();
    purchase.item = this.getItemType(item);
    purchase.transactionPrice = item.purchasePrice;
    this.shopService.addItemToCart(purchase);
  }

  rentItem(item: PublicationItem) {
    const rental = new Rental();
    rental.item = this.getItemType(item);
    rental.transactionPrice = item.rentalRate;
    this.shopService.addItemToCart(rental);
  }

  getItemType(item:PublicationItem){
    let type = item.publicationItemType;
    if(type == PublicationItemType.BOOK){
      return new Book(item);
    }
    else if(type == PublicationItemType.JOURNAL){
      return new Journal(item);
    }
    else if(type == PublicationItemType.LITERARY_PIECE){
      return new LiteraryPiece(item);
    }

    return item;
  }

  goBack() {
    this.router.navigateByUrl("/shop");
  }


  protected readonly headers = headers;
}

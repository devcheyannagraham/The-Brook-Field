import {Component, Input, signal} from '@angular/core';
import {CurrencyPipe, DatePipe} from "@angular/common";
import {ShopService} from '../../../Services/shop.service';
import {PublicationService} from '../../../Services/publication.service';
import {PublicationItem} from '../../../DTOs/Inventory/PublicationItem';
import {Publication} from '../../../DTOs/Inventory/Publication';
import {PublicationItemType} from '../../../Enums/PublicationItemType';
import {headers} from '../../../Helpers/headers';
import {Rental} from '../../../DTOs/Order/Rental';
import {Purchase} from '../../../DTOs/Order/Purchase';
import {Router} from '@angular/router';
import {Location} from '@angular/common';
import {CartComponent} from "../cart/cart.component";
import {SVGIconComponent} from '../../svgicon/svgicon.component';
import {
  FACTORY_TYPES,
  ITEM_TYPE_TABLE_HEADERS_SHOP,
  PUBLICATION_ITEM_TYPE_TABLE_HEADERS_SHOP
} from '../../../Helpers/globals';
import {ItemType} from '../../../Enums/ItemType';
import {FormatDataPipe} from '../../../Pipes/FormatDataPipe';


@Component({
  selector: 'shop-item-detail',
  imports: [
    DatePipe,
    CartComponent,
    SVGIconComponent,
    FormatDataPipe
  ],
  templateUrl: './shop-publication-detail.component.html',
  styleUrl: './shop-publication-detail.component.css'
})
export class ShopPublicationDetailComponent {
  publication: Publication;
  publicationItems = signal(new Map<number, PublicationItem>());
  publicationItemsInCart = new Map<number, number>();
  filteredPublicationItems = new Map<PublicationItemType, PublicationItem[]>();

  @Input() shopItemId: number;

  constructor(public shopService: ShopService, public publicationService: PublicationService, public router: Router, public location: Location) {
  }

  ngOnInit() {
    this.getPublicationData();
  }

  getPublicationData() {
    if (this.shopItemId) {
      this.publicationService.getPublicationById(this.shopItemId)
        .then(pub => {
          if (pub) {
            this.publication = pub;
            this.getPublicationItemsByPublicationId();
          }
        });
    }
  }

  getPublicationItemsByPublicationId() {
    if (this.shopItemId) {
      this.publicationService.getAvailablePublicationItemsByPublicationId(this.publication.publicationId)
        .then(pubItems => {
          if (pubItems) {
            let items = new Map<number, PublicationItem>();

            pubItems.forEach(item => {
              items.set(item.itemId, item);
              if (this.shopService.shoppingCart().has(item.itemId)) {
                this.updatePublicationItemTotals(item);
              }
            });
            this.publicationItems.set(items)
            this.filterPublicationItems();
          }
        });
    }
  }

  filterPublicationItems() {
    for (let itemType of Object.values(PublicationItemType)) {
      this.filteredPublicationItems.set(itemType, []);
    }
    for (let item of this.publicationItems().values()) {
      let itemGroup = this.filteredPublicationItems.get(item.publicationItemType);
      itemGroup.push(item);
    }
  }


  purchaseItem(item: PublicationItem) {
    const purchase = new Purchase();
    purchase.item = this.getItemType(item);
    purchase.transactionPrice = item.purchasePrice;
    this.shopService.addItemToCart(purchase);
    this.updatePublicationItemTotals(item);
  }

  rentItem(item: PublicationItem) {
    const rental = new Rental();
    rental.item = this.getItemType(item);
    rental.transactionPrice = item.rentalRate;
    this.shopService.addItemToCart(rental);
    this.updatePublicationItemTotals(item);
  }

  getItemType(item: PublicationItem) {
    let typeClass = FACTORY_TYPES.get(item.publicationItemType);
    return new typeClass(item);
  }

  removeItemFromCart(item: PublicationItem) {
    this.shopService.removeFromCart(item.itemId);

    if (this.publicationItemsInCart.has(item.publication.publicationId)) {
      this.publicationItemsInCart.set(item.publication.publicationId, this.publicationItemsInCart.get(item.publication.publicationId) - 1);
      if (this.publicationItemsInCart.get(item.publication.publicationId) <= 0) {
        this.publicationItemsInCart.set(item.publication.publicationId, 0);
      }
    }

  }

  getQuantity() {
    if (this.shopService.shoppingCart().size == 0) {
      return this.publicationItems().size;
    } else {
      return [...this.publicationItems().values()].filter(item => !this.shopService.shoppingCart().has(item.itemId)).length;
    }
  }

  updatePublicationItemTotals(item: PublicationItem) {
    if (this.publicationItemsInCart.has(item.publication.publicationId)) {
      this.publicationItemsInCart.set(item.publication.publicationId, this.publicationItemsInCart.get(item.publication.publicationId) + 1);
    } else {
      this.publicationItemsInCart.set(item.publication.publicationId, 1);
    }

  }


  protected readonly headers = headers;
  protected readonly PublicationItemType = PublicationItemType;
  protected readonly Object = Object;
  protected readonly ITEM_TYPE_TABLE_HEADERS_SHOP = ITEM_TYPE_TABLE_HEADERS_SHOP;
  protected readonly ItemType = ItemType;
  protected readonly PUBLICATION_ITEM_TYPE_TABLE_HEADERS_SHOP = PUBLICATION_ITEM_TYPE_TABLE_HEADERS_SHOP;
}

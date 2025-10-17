import { Component, Input, signal } from '@angular/core';
import { AccessoryService } from '../../../Services/accessory.service';
import { ShopService } from '../../../Services/shop.service';
import { Router } from '@angular/router';
import { Accessory } from '../../../DTOs/Accessory/Accessory';
import { AccessoryItem } from '../../../DTOs/Accessory/AccessoryItem';
import { headers } from '../../../Helpers/headers';
import { Purchase } from '../../../DTOs/Order/Purchase';
import { CurrencyPipe, Location } from '@angular/common';
import { CartComponent } from "../cart/cart.component";

@Component({
  selector: 'shop-accessory-detail',
  imports: [
    CartComponent,
    CurrencyPipe
  ],
  templateUrl: './shop-accessory-detail.component.html',
  styleUrl: './shop-accessory-detail.component.css'
})
export class ShopAccessoryDetailComponent {
  @Input() shopItemId: number;
  accessory: Accessory;
  accessoryItemsInCart = new Map<number, AccessoryItem>();
  accessoryItemsNotInCart = new Map<number, AccessoryItem>();
  quantity = 0;


  constructor(public accessoryService: AccessoryService, public shopService: ShopService, public router: Router, public location: Location) { }

  ngOnInit() {
    this.getAccessory();
  }

  getAccessory() {
    if (this.shopItemId) {
      this.accessoryService.getAccessoryById(this.shopItemId)
        .then(acc => {
          if (acc) {
            this.accessory = acc || null;
            return this.accessoryService.getAvailableAccessoryItemsByAccessoryId(acc.accessoryId)
          } else return null;
        })
        .then(items => {
          if (items) {
            this.mapAccItems(items);
          }
        }
        );
    }
  }

  mapAccItems(accItems: AccessoryItem[]) {
    accItems.forEach(item => {

      //Track cart access items by accessID
      if (this.shopService.shoppingCart().has(item.itemId)) this.accessoryItemsInCart.set(item.itemId, item);
      else this.accessoryItemsNotInCart.set(item.itemId, item);
      this.getQuantity();
    });
  }

  purchaseAccessoryItem() {
    const purchase = new Purchase();
    const accItemId = this.accessoryItemsNotInCart.keys().next().value;
    const accItem = this.accessoryItemsNotInCart.get(accItemId);
    if (accItem) {
      this.accessoryItemsNotInCart.delete(accItemId);
      purchase.item = new AccessoryItem(accItem);
      purchase.transactionPrice = this.accessory.price;
      this.shopService.addItemToCart(purchase);
      this.accessoryItemsInCart.set(accItem.itemId, accItem);
      this.getQuantity();
    } else alert("No accessory items available to add to cart.");
  }

  removeAccessoryItem() {
    const accItemId = this.accessoryItemsInCart.keys().next().value;
    let accItem = this.accessoryItemsInCart.get(accItemId)
    this.shopService.removeFromCart(accItem.itemId);
    this.accessoryItemsInCart.delete(accItemId);
    this.accessoryItemsNotInCart.set(accItem.itemId, accItem);
    this.getQuantity();
  }

  getQuantity() {
    this.quantity = this.accessoryItemsNotInCart.size;
  }


  protected readonly headers = headers;
}

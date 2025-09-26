import { Component, Input, signal } from '@angular/core';
import { AccessoryService } from '../../../Services/accessory.service';
import { ShopService } from '../../../Services/shop.service';
import { Router, RouterLink } from '@angular/router';
import { Accessory } from '../../../DTOs/Accessory/Accessory';
import { AccessoryItem } from '../../../DTOs/Accessory/AccessoryItem';
import { headers } from '../../../Helpers/headers';
import { Purchase } from '../../../DTOs/Order/Purchase';
import { Location } from '@angular/common';

@Component({
  selector: 'shop-accessory-detail',
  imports: [
    RouterLink
  ],
  templateUrl: './shop-accessory-detail.component.html',
  styleUrl: './shop-accessory-detail.component.css'
})
export class ShopAccessoryDetailComponent {
  @Input() shopItemId: number;
  accessory: Accessory;
  accessoryItems = signal(new Map<number, AccessoryItem>());
  accessoryItemsInCart = new Map<number, number[]>;


  constructor(public accessoryService: AccessoryService, public shopService: ShopService, public router: Router, public location:Location) { }

  ngOnInit() {
    this.getAccessory();
  }

  getAccessory() {
    if (this.shopItemId) {
      this.accessoryService.getAccessoryById(this.shopItemId)
        .subscribe(acc => {
          this.accessory = acc;
          this.accessoryService.getAvailableAccessoryItemsByAccessoryId(acc.accessoryId)
            .subscribe(items => {
              let accessItems = new Map<number, AccessoryItem>();
              items.forEach(item => {
                accessItems.set(item.itemId, item);

                //Track cart access items by accessID
                if (this.shopService.shoppingCart().has(item.itemId)) {
                  if (this.accessoryItemsInCart.has(acc.accessoryId)) {
                    this.accessoryItemsInCart.get(acc.accessoryId).push(item.itemId);
                  } else {
                    this.accessoryItemsInCart.set(acc.accessoryId, [item.itemId]);

                  }
                }
              });
              this.accessoryItems.set(accessItems);
            })
        });
    }
  }

  purchaseAccessoryItem() {
    const purchase = new Purchase();
    // @ts-ignore 7053
    const accItem = [...this.accessoryItems().values()].pop(); // still in map
    purchase.item = new AccessoryItem(accItem);
    purchase.transactionPrice = this.accessory.price;
    this.shopService.addItemToCart(purchase);
    if(this.accessoryItemsInCart.has(accItem.accessory.accessoryId)){
      this.accessoryItemsInCart.get(accItem.accessory.accessoryId).push(accItem.itemId);
    } else {
      this.accessoryItemsInCart.set(accItem.accessory.accessoryId, [accItem.itemId]);
    }
  }

  removeAccessoryItem(){
    let accItemId =  this.accessoryItemsInCart.get(this.accessory.accessoryId).pop();
    this.shopService.removeFromCart(accItemId);   
  }

  getQuantity() {
    if (this.shopService.shoppingCart().size == 0) {
      return this.accessoryItems().size;
    }
    else {
      return [...this.accessoryItems().values()].filter(item => !this.shopService.shoppingCart().has(item.itemId)).length;
    }

  }


  protected readonly headers = headers;
}

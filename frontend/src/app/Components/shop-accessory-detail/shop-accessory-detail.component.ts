import {Component, Input} from '@angular/core';
import {AccessoryService} from '../../Services/accessory.service';
import {ShopService} from '../../Services/shop.service';
import {Router, RouterLink} from '@angular/router';
import {Accessory} from '../../DTOs/Accessory/Accessory';
import {AccessoryItem} from '../../DTOs/Accessory/AccessoryItem';
import {headers} from '../../Helpers/headers';
import {Purchase} from '../../DTOs/Order/Purchase';

@Component({
  selector: 'shop-accessory-detail',
  imports: [
    RouterLink
  ],
  templateUrl: './shop-accessory-detail.component.html',
  styleUrl: './shop-accessory-detail.component.css'
})
export class ShopAccessoryDetailComponent {
  @Input () shopItemId: number;
  accessory: Accessory;
  accessoryItems: AccessoryItem[];

  constructor(public accessoryService: AccessoryService, public shopService: ShopService, public router:Router){}

  ngOnInit(){
    this.getAccessory();
  }

  getAccessory(){
    if(this.shopItemId){
      this.accessoryService.getAccessory(this.shopItemId)
        .subscribe(acc => {
          this.accessory = acc;
          this.accessoryService.getAvailableAccessoryItemsByAccessoryId(acc.accessoryId)
            .subscribe(items => {
              this.accessoryItems = items;
            })
        });
    }
  }

  purchaseAccessoryItem(){
    const purchase = new Purchase();
    const accItem = this.accessoryItems.pop();
    purchase.item = new AccessoryItem(accItem);
    purchase.transactionPrice = this.accessory.price;
    console.log("ACC Purchase", purchase);

    this.shopService.addItemToCart(purchase);
  }


  goBack() {
    this.router.navigateByUrl("/shop");
  }

  protected readonly headers = headers;
}

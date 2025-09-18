import {Routes} from '@angular/router';
import {PublicationFormComponent} from './Forms/publication-form/publication-form.component';
import {AccessoryFormComponent} from './Forms/accessory-form/accessory-form.component';
import {PublicationItemsComponent} from './Components/publication-items/publication-items.component';
import {PublicationsComponent} from './Components/publications/publications.component';
import {PublicationItemFormComponent} from './Forms/publication-item-form/publication-item-form.component';
import {AccessoriesComponent} from './Components/accessories/accessories.component';
import {AccessoryItemsComponent} from './Components/accessory-items/accessory-items.component';
import {OrderFormComponent} from './Forms/order-form/order-form.component';
import {ShopComponent} from './Components/shop/shop.component';
import {ShopPublicationDetailComponent} from './Components/shop-publication-detail/shop-publication-detail.component';
import {ShopAccessoryDetailComponent} from './Components/shop-accessory-detail/shop-accessory-detail.component';
import { PopularItemsComponent } from './Components/popular-items/popular-items.component';
import { LowInventoryComponent } from './Components/low-inventory/low-inventory.component';
import { RecentOrdersComponent } from './Components/recent-orders/recent-orders.component';

export const routes: Routes = [
  {
    path: 'publicationitemform/publication/:publicationId',
    component: PublicationItemFormComponent,
  },
  {
    path: 'publicationitemform/:pubItemId',
    component: PublicationItemFormComponent,
  },
  {
    path: 'publicationitemform',
    component: PublicationItemFormComponent,
  },
  {
    path: 'publicationform/:publicationId',
    component: PublicationFormComponent,
  },
  {
    path: 'publicationform',
    component: PublicationFormComponent,
  },
  {
    path: 'publication/:publicationId',
    component: PublicationItemsComponent,
  },
  {
    path: 'publications',
    component: PublicationsComponent,
  },
  {
    path: 'accessory/:accessId',
    component: AccessoryItemsComponent,
  },
  {
    path: 'accessories',
    component: AccessoriesComponent,
  },
  {
    path: 'accessoryform/:accessoryId',
    component: AccessoryFormComponent,
  },
  {
    path: 'accessoryform',
    component: AccessoryFormComponent,
  },
  {
    path: 'checkout',
    component: OrderFormComponent,
  },
  {
    path: 'shop',
    component: ShopComponent,
  },
  {
    path: 'shop/publication/:shopItemId',
    component: ShopPublicationDetailComponent,
  },
  {
    path: 'shop/accessory/:shopItemId',
    component: ShopAccessoryDetailComponent,
  },
  {
    path: 'report/popularitems',
    component: PopularItemsComponent
  },
  {
    path: 'report/lowinventory',
    component: LowInventoryComponent
  }, 
  {
    path: 'report/recentorders',
    component: RecentOrdersComponent
  }
];

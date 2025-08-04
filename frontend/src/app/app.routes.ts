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
import {ShopItemDetailComponent} from './Components/shop-item-detail/shop-item-detail.component';

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
    path: 'orderform',
    component: OrderFormComponent,
  },
  {
    path: 'shop',
    component: ShopComponent,
  },
  {
    path: 'shop/:shopItemId',
    component: ShopItemDetailComponent,
  },
];

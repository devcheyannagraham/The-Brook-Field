import {Routes} from '@angular/router';
import {PublicationFormComponent} from './Forms/publication-form/publication-form.component';
import {AccessoryFormComponent} from './Forms/accessory-form/accessory-form.component';
import {PublicationItemsComponent} from './Components/publication-items/publication-items.component';
import {PublicationsComponent} from './Components/publications/publications.component';
import {PublicationItemFormComponent} from './Forms/publication-item-form/publication-item-form.component';

export const routes: Routes = [
  {
    path: 'publicationform/:publicationId',
    component: PublicationFormComponent,
  },
  {
    path: 'publicationform',
    component: PublicationFormComponent,
  },
  {
    path: 'accessoryform',
    component: AccessoryFormComponent,
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
    path: 'publicationitemform/:pubItemId',
    component: PublicationItemFormComponent,
  },
  {
    path: 'publicationitemform',
    component: PublicationItemFormComponent,
  },
];

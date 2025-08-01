import {Routes} from '@angular/router';
import {AppComponent} from './app.component';
import {PublicationFormComponent} from './Forms/publication-form/publication-form.component';
import {AccessoryFormComponent} from './Forms/accessory-form/accessory-form.component';
import {PublicationItemsComponent} from './Components/publication-items/publication-items.component';
import {PublicationsComponent} from './Components/publications/publications.component';

export const routes: Routes = [
  {
    path: 'publicationform',
    component: PublicationFormComponent,
  },
  {
    path: 'accessoryform',
    component: AccessoryFormComponent,
  },
  {
    path: 'publication/:id',
    component: PublicationItemsComponent,
  },
  {
    path: 'publications',
    component: PublicationsComponent,
  },
];

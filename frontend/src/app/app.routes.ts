import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { PublicationFormComponent } from './Forms/publication-form/publication-form.component';
import { AccessoryFormComponent } from './Forms/accessory-form/accessory-form.component';

export const routes: Routes = [
  {
    path: 'publicationform',
    component: PublicationFormComponent,
  },
  {
    path: 'accessoryform',
    component: AccessoryFormComponent,
  },
];

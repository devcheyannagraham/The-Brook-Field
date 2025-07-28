import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { PublicationFormComponent } from './Forms/publication-form/publication-form.component';

export const routes: Routes = [
  {
    path: 'publication',
    component: PublicationFormComponent,
  },
];

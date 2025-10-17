import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Publication } from '../DTOs/Inventory/Publication';
import { PublicationItem } from '../DTOs/Inventory/PublicationItem';
import { AuthService } from './auth.service';
import { firstValueFrom } from 'rxjs';
import { ToasterService } from './toaster.service';
import {BASE_URL} from '../Helpers/globals';

@Injectable({
  providedIn: 'root',
})
export class PublicationService {


  constructor(private http: HttpClient, private authService: AuthService, public toaster: ToasterService) {
  }

  //Get all pub items for specific pub
  getPublicationItemsByPublicationId(pubId: number) {
    return firstValueFrom(this.http.get<any[]>(`${BASE_URL}publicationitems/${pubId}`, { withCredentials: true }))
      .then(items => items)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

  //Get available pub items for specific pub
  getAvailablePublicationItemsByPublicationId(pubId: number) {
    return firstValueFrom(this.http.get<any[]>(`${BASE_URL}shop/publicationitems/${pubId}`))
      .then(items => items)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

  //Get all pubs for list view
  getPublications() {
    return firstValueFrom(this.http.get<Publication[]>(`${BASE_URL}publications`))
      .then(items => items)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

  //Get 1 publication
  getPublicationById(id: number) {
    return firstValueFrom(this.http.get<Publication>(`${BASE_URL}publication/${id}`))
      .then(items => items)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }


  // Get 1 pub item
  getPublicationItemById(pubItemId: number) {
    return firstValueFrom(this.http.get<PublicationItem>(`${BASE_URL}publicationitem/${pubItemId}`, { withCredentials: true }))
      .then(items => items)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }


  newPublication(pub: any) {
    return firstValueFrom(this.http.post(`${BASE_URL}publication`, pub, { withCredentials: true }))
      .then(items => {
        this.toaster.message.set({ class: "success", message: "Publication Added." })
        return items;
      })
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

  newPublicationItem(pubItem: any) {
    return firstValueFrom(this.http.post(`${BASE_URL}publicationitem`, pubItem, { withCredentials: true }))
      .then(items => {
        this.toaster.message.set({ class: "success", message: "Publication Item Added." })
        return items;
      })
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

  deletePublicationItem(itemId: number) {
    return firstValueFrom(this.http.delete(`${BASE_URL}publicationitem/${itemId}`, { withCredentials: true }))
      .then(items => {
        this.toaster.message.set({ class: "success", message: "Publication Item Deleted." })
        return items;
      })
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

  deletePublication(pubId: number) {
    return firstValueFrom(this.http.delete(`${BASE_URL}publication/${pubId}`, { withCredentials: true }))
      .then(items => {
        this.toaster.message.set({ class: "success", message: "Publication Deleted." })
        return items;
      })
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));

  }


}

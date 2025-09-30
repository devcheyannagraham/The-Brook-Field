import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Publication } from '../DTOs/Inventory/Publication';
import { PublicationItem } from '../DTOs/Inventory/PublicationItem';
import { AuthService } from './auth.service';
import { firstValueFrom } from 'rxjs';
import { ToasterService } from './toaster.service';

@Injectable({
  providedIn: 'root',
})
export class PublicationService {
  baseUrl: string = 'https://localhost:8080/';


  constructor(private http: HttpClient, private authService: AuthService, public toaster: ToasterService) {
  }

  //Get all pub items for specific pub
  getPublicationItemsByPublicationId(pubId: number) {
    return firstValueFrom(this.http.get<any[]>(`${this.baseUrl}publicationitems/${pubId}/${this.authService.user().userId}`, { withCredentials: true }))
      .then(items => items)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

  //Get available pub items for specific pub
  getAvailablePublicationItemsByPublicationId(pubId: number) {
    return firstValueFrom(this.http.get<any[]>(`${this.baseUrl}shop/publicationitems/${pubId}`))
      .then(items => items)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

  //Get all pubs for list view
  getPublications() {
    return firstValueFrom(this.http.get<Publication[]>(`${this.baseUrl}publications`))
      .then(items => items)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

  //Get 1 publication
  getPublicationById(id: number) {
    return firstValueFrom(this.http.get<Publication>(`${this.baseUrl}publication/${id}`))
      .then(items => items)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }


  // Get 1 pub item
  getPublicationItemById(pubItemId: number) {
    return firstValueFrom(this.http.get<PublicationItem>(`${this.baseUrl}publicationitem/${pubItemId}/${this.authService.user().userId}`, { withCredentials: true }))
      .then(items => items)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }


  newPublication(pub: any) {
    return firstValueFrom(this.http.post(`${this.baseUrl}publication/${this.authService.user().userId}`, pub, { withCredentials: true }))
      .then(items => {
        this.toaster.message.set({ class: "success", message: "Publication Added."})
        return items;
      })
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

  newPublicationItem(pubItem: any) {
    return firstValueFrom(this.http.post(`${this.baseUrl}publicationitem/${this.authService.user().userId}`, pubItem, { withCredentials: true }))
      .then(items => {
        this.toaster.message.set({ class: "success", message: "Publication Item Added."})
        return items;
      })
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

  deletePublicationItem(itemId: number) {
    return firstValueFrom(this.http.delete(`${this.baseUrl}publicationitem/${itemId}/${this.authService.user().userId}`, { withCredentials: true }))
      .then(items => {
        this.toaster.message.set({ class: "success", message: "Publication Item Deleted."})
        return items;
      })
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }

  deletePublication(pubId: number) {
    return firstValueFrom(this.http.delete(`${this.baseUrl}publication/${pubId}/${this.authService.user().userId}`, { withCredentials: true }))
      .then(items => {
        this.toaster.message.set({ class: "success", message: "Publication Deleted."})
        return items;
      })
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));

  }


}

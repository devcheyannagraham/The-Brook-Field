import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Publication } from '../DTOs/Inventory/Publication';
import { PublicationItem } from '../DTOs/Inventory/PublicationItem';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class PublicationService {
  baseUrl: string = 'http://localhost:8080/';

  constructor(private http: HttpClient, private authService: AuthService) {
  }

  //Get all pub items for specific pub
  getPublicationItemsByPublicationId(pubId: number) {
    return this.http.get<any[]>(`${this.baseUrl}publicationitems/${pubId}/${this.authService.user().userId}`, { withCredentials: true });
  }

  //Get available pub items for specific pub
  getAvailablePublicationItemsByPublicationId(pubId: number) {
    return this.http.get<any[]>(`${this.baseUrl}shop/publicationitems/${pubId}`);
  }

  //Get all pubs for list view
  getPublications() {
    return this.http.get<Publication[]>(`${this.baseUrl}publications`);
  }

  //Get 1 publication
  getPublicationById(id: number) {
    return this.http.get<Publication>(`${this.baseUrl}publication/${id}`);
  }


  // Get 1 pub item
  getPublicationItemById(pubItemId: number) {
    return this.http.get<PublicationItem>(`${this.baseUrl}publicationitem/${pubItemId}/${this.authService.user().userId}`, { withCredentials: true });
  }


  newPublication(pub: any) {
    return this.http
      .post(`${this.baseUrl}publication/${this.authService.user().userId}`, pub, { withCredentials: true });
  }

  newPublicationItem(pubItem: any) {
    return this.http.post(`${this.baseUrl}publicationitem/${this.authService.user().userId}`, pubItem, { withCredentials: true });
  }

  deletePublicationItem(itemId: number) {
    return this.http.delete(`${this.baseUrl}publicationitem/${itemId}/${this.authService.user().userId}`, { withCredentials: true });
  }

  deletePublication(pubId: number) {
    return this.http.delete(`${this.baseUrl}publication/${pubId}/${this.authService.user().userId}`, { withCredentials: true });

  }


}

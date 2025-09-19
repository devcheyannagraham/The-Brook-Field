import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Publication} from '../DTOs/Inventory/Publication';
import {PublicationItem} from '../DTOs/Inventory/PublicationItem';

@Injectable({
  providedIn: 'root',
})
export class PublicationService {
  baseUrl: string = 'http://localhost:8080/';

  constructor(private http: HttpClient) {
  }

  newPublication(pub: any) {
    return this.http
      .post(`${this.baseUrl}publication`, pub);
  }

  newPublicationItem(pubItem:any)
  {
    return this.http.post(`${this.baseUrl}publicationitem`, pubItem);
  }

  getPublications() {
    return this.http.get<Publication[]>(`${this.baseUrl}publications`);
  }

  getPublicationById(id: number) {
    return this.http.get<Publication>(`${this.baseUrl}publication/${id}`);
  }

  getPublicationItemsByPublicationId(pubId: number) {
    return this.http.get<any[]>(`${this.baseUrl}publicationitems/${pubId}`);
  }

  getPublicationItemById(pubItemId: number){
    return this.http.get<PublicationItem>(`${this.baseUrl}publicationitem/${pubItemId}`);
  }

  deletePublicationItem(itemId: number){
    return this.http.delete(`${this.baseUrl}publicationitem/${itemId}`);
  }

  deletePublication(pubId: number){
    return this.http.delete(`${this.baseUrl}publication/${pubId}`);

  }


}

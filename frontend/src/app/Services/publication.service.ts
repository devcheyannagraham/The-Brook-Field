import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Publication} from '../DTOs/Inventory/Publication';
import {PublicationItem} from '../DTOs/Inventory/PublicationItem';

@Injectable({
  providedIn: 'root',
})
export class PublicationService {
  baseUrl: String = 'http://localhost:8080/';

  constructor(private http: HttpClient) {
  }

  newPublication(pub: any) {
    console.log("PUB", pub)
    return this.http
      .post(`${this.baseUrl}publication`, pub);
  }

  newPublicationItem(pubItem:any)
  {
    console.log("PUBITEM: ", pubItem)
    return this.http.post(`${this.baseUrl}publicationitem`, pubItem);
  }

  getPublications() {
    return this.http.get<Publication[]>(`${this.baseUrl}publications`);
  }

  getPublicationById(id: Number) {
    return this.http.get<Publication>(`${this.baseUrl}publication/${id}`);
  }

  getPublicationItemsByPublicationId(pubId: Number) {
    return this.http.get<any[]>(`${this.baseUrl}publicationitems/${pubId}`);
  }

  getPublicationItemById(pubItemId: Number){
    return this.http.get<PublicationItem>(`${this.baseUrl}publicationitem/${pubItemId}`);
  }

  deletePublicationItem(itemId:Number){
    return this.http.delete(`${this.baseUrl}publicationitem/${itemId}`);
  }

  deletePublication(pubId:Number){
    return this.http.delete(`${this.baseUrl}publication/${pubId}`);

  }


}

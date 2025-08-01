import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Publication} from '../DTOs/Inventory/Publication';
import {PublicationItem} from '../DTOs/Inventory/PublicationItem';

@Injectable({
  providedIn: 'root',
})
export class PublicationService {
  http: HttpClient;
  baseUrl: String = 'http://localhost:8080/';

  constructor(private httpClient: HttpClient) {
    this.http = httpClient;
  }

  newPublication(pub: any) {
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

  deleteItem(itemId:Number){
    return this.http.delete(`${this.baseUrl}publicationitem/delete/${itemId}`);
  }


}

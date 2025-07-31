import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Publication} from '../DTOs/Inventory/Publication';
import {publishFacade} from '@angular/compiler';
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

  newPublication(publicationData: any) {
    console.log('pub data\n', publicationData);

    return this.http
      .post(`${this.baseUrl}publicationitem`, publicationData);
  }

  getPublications() {
    return this.http.get<Publication>(`${this.baseUrl}publications`);
  }

  getPublicationById(id: Number) {
    return this.http.get<Publication>(`${this.baseUrl}publication/${id}`);
  }

  getPublicationItemsByPublicationId(pubId: Number) {
    return this.http.get<any[]>(`${this.baseUrl}publicationitems/${pubId}`);
  }


}

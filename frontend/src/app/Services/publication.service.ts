import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class PublicationService {
  http: HttpClient;
  baseUrl: String = 'http://localhost:8080/';

  constructor(private httpClient: HttpClient) {
    this.http = httpClient;
  }

  async newPublication(publicationData: any) {
    console.log('pub data\n', publicationData);
    const observer = {
      next: (resp: any) => resp,
      error: (error: HttpErrorResponse) => error,
    };

    return this.http
      .post(`${this.baseUrl}publicationitem`, publicationData)
      .subscribe(observer);
  }
}

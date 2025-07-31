import { Component } from '@angular/core';
import { PublicationService } from '../../Services/publication.service';
import { Publication } from '../../DTOs/Inventory/Publication';
import { DatePipe, KeyValuePipe } from '@angular/common';

@Component({
  selector: 'publications',
  imports: [KeyValuePipe, DatePipe],
  templateUrl: './publications.component.html',
  styleUrl: './publications.component.css',
})
export class PublicationsComponent {
  publications: any;
  headers = {
    title: 'Title',
    datePublished: 'Date Published',
    isbn: 'ISBN',
    quantity: 'Quantity', // maybe delete
    genre: 'Genre',
  };

  constructor(public pubService: PublicationService) {}

  ngOnInit() {
    this.getPublications();
  }

  getPublications() {
    this.pubService.getPublications().subscribe((data: any) => {
      this.publications = data;
      console.log(this.publications);
    });
  }
}

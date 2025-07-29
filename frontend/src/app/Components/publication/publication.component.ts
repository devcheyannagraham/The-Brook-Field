import { Component } from '@angular/core';
import { PublicationService } from '../../Services/publication.service';
import { Publication } from '../../DTOs/Inventory/Publication';
import { DatePipe, KeyValuePipe } from '@angular/common';

@Component({
  selector: 'publication',
  imports: [KeyValuePipe, DatePipe],
  templateUrl: './publication.component.html',
  styleUrl: './publication.component.css',
})
export class PublicationComponent {
  publications: any;
  headers = {
    title: 'Title',
    datePublished: 'Date Published',
    isbn: 'ISBN',
    quantity: 'Quantity',
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

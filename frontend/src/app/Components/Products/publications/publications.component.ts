import {Component} from '@angular/core';
import {PublicationService} from '../../../Services/publication.service';
import {DatePipe} from '@angular/common';
import {headers} from '../../../Helpers/headers';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'publications',
  imports: [DatePipe, RouterLink],
  templateUrl: './publications.component.html',
  styleUrl: './publications.component.css',
})
export class PublicationsComponent {
  publications: any;
  headers = headers;

  constructor(public pubService: PublicationService) {
  }

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

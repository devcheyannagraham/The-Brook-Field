import { Component } from '@angular/core';
import { PublicationService } from '../../../Services/publication.service';
import { DatePipe } from '@angular/common';
import { headers } from '../../../Helpers/headers';
import { RouterLink } from '@angular/router';
import { SVGIconComponent } from "../../svgicon/svgicon.component";

@Component({
  selector: 'publications',
  imports: [DatePipe, RouterLink, SVGIconComponent],
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
    this.pubService.getPublications()
      .then((data: any) => {
        if (data) this.publications = data;
      });
  }

}

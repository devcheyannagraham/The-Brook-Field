import { Component, Input } from '@angular/core';
import { PublicationService } from '../../../Services/publication.service';
import { PublicationItem } from '../../../DTOs/Inventory/PublicationItem';
import { Publication } from '../../../DTOs/Inventory/Publication';
import { Journal } from '../../../DTOs/Inventory/Journal';
import { PublicationItemType } from '../../../Enums/PublicationItemType';
import { LiteraryPiece } from '../../../DTOs/Inventory/LiteraryPiece';
import { Book } from '../../../DTOs/Inventory/Book';
import { headers } from '../../../Helpers/headers';
import { CurrencyPipe, DatePipe } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { SVGIconComponent } from '../../svgicon/svgicon.component';

@Component({
  selector: 'publication-items',
  imports: [
    DatePipe,
    RouterLink,
    SVGIconComponent,
    CurrencyPipe
  ],
  templateUrl: './publication-items.component.html',
  styleUrl: './publication-items.component.css'
})
export class PublicationItemsComponent {
  publication: Publication;
  publicationItems: PublicationItem[];
  books: Book[];
  journals: Journal[];
  literaryPieces: LiteraryPiece[]
  @Input() publicationId: number;


  //For Template
  PublicationItemType = PublicationItemType;
  Journal = Journal;
  headers = headers;

  constructor(public pubService: PublicationService, public router: Router) {
  }

  ngOnInit() {
    this.getPublicationData();
  }

  getPublicationData() {
    if (this.publicationId) {
      this.pubService.getPublicationById(this.publicationId)
        .then(pub => {
          if (pub) {
            this.publication = pub;
            this.getPublicationItemsByPublicationId();
          }
        });
    }
  }

  getPublicationItemsByPublicationId() {
    if (this.publicationId) {
      this.pubService.getPublicationItemsByPublicationId(this.publication.publicationId)
        .then(pubItems => {
          if (pubItems) {
            this.publicationItems = pubItems;
            this.filterPublicationItems();
          }
        });
    }
  }

  filterPublicationItems() {
    this.books = this.publicationItems
      .filter(item => item.publicationItemType === PublicationItemType.BOOK)
      .map(item => item as Book);
    this.journals = this.publicationItems
      .filter(item => item.publicationItemType === PublicationItemType.JOURNAL)
      .map(item => item as Journal);
    this.literaryPieces = this.publicationItems
      .filter(item => item.publicationItemType === PublicationItemType.LITERARY_PIECE)
      .map(item => item as LiteraryPiece);
  }

  deleteItem(itemId: number) {
    this.pubService.deletePublicationItem(itemId)
      .then(result => {
        if (result) {
          //update items
          this.getPublicationItemsByPublicationId();
        }
      });
  }

  deletePublication(pubId: number) {
    this.pubService.deletePublication(pubId)
      .then(result => {
        if (result) {
          this.router.navigateByUrl("/publications");
        }
      })
  }

}

import {Component, Input} from '@angular/core';
import {PublicationService} from '../../Services/publication.service';
import {PublicationItem} from '../../DTOs/Inventory/PublicationItem';
import {Publication} from '../../DTOs/Inventory/Publication';
import {Journal} from '../../DTOs/Inventory/Journal';
import {PublicationItemType} from '../../Enums/PublicationItemType';
import {LiteraryPiece} from '../../DTOs/Inventory/LiteraryPiece';
import {Book} from '../../DTOs/Inventory/Book';
import {headers} from '../../Helpers/headers';
import {DatePipe} from '@angular/common';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'publication-items',
  imports: [
    DatePipe,
    RouterLink
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
  @Input() publicationId: Number;


  //For Template
  PublicationItemType = PublicationItemType;
  Journal = Journal;
  headers = headers;

  constructor(public pubService: PublicationService) {
  }

  ngOnInit() {
    this.getPublicationData();
  }

  getPublicationData() {
    // catch error;
    if (this.publication == null) {
      this.pubService.getPublicationById(this.publicationId)
        .subscribe(pub => {
          this.publication = pub;
          this.getPublicationItemsByPublicationId();
        })
    } else this.getPublicationItemsByPublicationId()
  }

  getPublicationItemsByPublicationId() {
    this.pubService.getPublicationItemsByPublicationId(this.publication.publicationId)
      .subscribe(pubItems => {
        if (pubItems) {
          this.publicationItems = pubItems;
          this.filterPublicationItems();
        }
      });

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

  deleteItem(itemId: Number) {
    this.pubService.deleteItem(itemId)
      .subscribe(result => {
        if (result) {
          //update items
          this.getPublicationItemsByPublicationId();
        }
      });
  }

}

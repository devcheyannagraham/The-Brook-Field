import {Component} from '@angular/core';
import {DatePipe, NgIf} from '@angular/common';
import {PublicationService} from '../../Services/publication.service';
import {PublicationItem} from '../../DTOs/Inventory/PublicationItem';
import {Publication} from '../../DTOs/Inventory/Publication';
import {Journal} from '../../DTOs/Inventory/Journal';
import {PublicationItemType} from '../../Enums/PublicationItemType';
import {TypeCast} from '../../Pipes/TypeCast';

@Component({
  selector: 'publication-items',
  imports: [
    DatePipe,
    NgIf,
    TypeCast
  ],
  templateUrl: './publication-items.component.html',
  styleUrl: './publication-items.component.css'
})
export class PublicationItemsComponent {
  publication!: Publication;
  publicationItems!: PublicationItem[];
  publicationId: Number = 3;

  //For Template
  PublicationItemType = PublicationItemType;
  Journal = Journal;
  public instanceof: any;


  constructor(public pubService: PublicationService) {
  }

  ngOnInit() {
    this.getPublicationData();
  }

  getPublicationData() {
    // catch error;
    if (!this.publication) {
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
          console.log(pubItems)
          for(let item of pubItems){
           let j = item as Journal;
           console.log("J: ", j.issueDate)
          }
        }
      })

  }


}

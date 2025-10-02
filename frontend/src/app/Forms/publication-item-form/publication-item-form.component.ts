import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from "@angular/forms";
import { PublicationService } from '../../Services/publication.service';
import { PublicationItemType } from '../../Enums/PublicationItemType';
import { PublicationItem } from '../../DTOs/Inventory/PublicationItem';
import { PublicationItemStatus } from '../../Enums/PublicationItemStatus';
import { Journal } from '../../DTOs/Inventory/Journal';
import { LiteraryPiece } from '../../DTOs/Inventory/LiteraryPiece';
import { Book } from '../../DTOs/Inventory/Book';
import { Publication } from '../../DTOs/Inventory/Publication';
import { PublicationItemFormat } from '../../Enums/PublicationItemFormat';
import { LiteraryType } from '../../Enums/LiteraryType';
import { Router } from '@angular/router';
import { DatePipe, Location } from '@angular/common';
import { headers } from '../../Helpers/headers';

@Component({
  selector: 'publication-item-form',
  imports: [
    FormsModule,
    ReactiveFormsModule,
    DatePipe
  ],
  templateUrl: './publication-item-form.component.html',
  styleUrl: './publication-item-form.component.css'
})
export class PublicationItemFormComponent {
  publicationItemForm: FormGroup;
  @Input() pubItemId: number;
  @Input() publicationId: number;
  publicationItem: PublicationItem;
  publication: Publication;

  constructor(public formBuilder: FormBuilder, public pubService: PublicationService, public router: Router, public location: Location) {
  }

  ngOnInit() {
    this.createForm();

    //Populate form on update
    if (this.pubItemId) {
      this.getPublicationItem();
    }

    //Fetch Pub info to display
    //New Pub Item
    if (this.publicationId) {
      this.getPublication();
    }
  }

  getPublicationItem() {
    this.pubService.getPublicationItemById(this.pubItemId)
      .then(pubItem => {
        if (pubItem) {
          this.publicationItem = pubItem;
          this.publication = this.publicationItem.publication;
          this.fillForm();
        }
      });
  }

  getPublication() {
    this.pubService.getPublicationById(this.publicationId)
      .then(pub => {
        if (pub) this.publication = pub;
      });
  }


  createForm() {
    this.publicationItemForm = this.formBuilder.group({
      //PublicationItem fields
      publicationItemType: [],
      quantity: [1],
      edition: [''],
      format: [''],
      purchasePrice: [''],
      rentalRate: [''],
      publicationItemStatus: [''],

      //No additional Book Fields
      //Journal Fields
      issueDate: [''],
      issueNumber: [''],
      issueName: [''],
      volume: [''],

      //Literary Piece
      literaryType: [''],
    });

  }

  fillForm() {
    if (this.publicationItem) {
      this.publicationItemForm.patchValue(this.publicationItem);
    }
  }

  addPublicationItem() {
    const formData = this.publicationItemForm.value;
    const pubType = formData.publicationItemType;
    let publicationItem = pubType === PublicationItemType.JOURNAL ? new Journal(formData) :
      pubType == PublicationItemType.BOOK ? new Book(formData) :
        pubType == PublicationItemType.LITERARY_PIECE ? new LiteraryPiece(formData) :
          new PublicationItem(formData);

    if (this.pubItemId) publicationItem.itemId = this.pubItemId;
    publicationItem.publication = this.publication;

    this.createPublicationItem(publicationItem);
  }


  createPublicationItem(item: any) {
    this.pubService.newPublicationItem(item)
      .then(resp => {
        // @ts-ignore
        this.router.navigateByUrl(`/publication/${resp[0]["publication"]["publicationId"]}`)
      })
  }

  protected readonly PublicationItemType = PublicationItemType;
  protected readonly PublicationItemFormat = PublicationItemFormat;
  protected readonly LiteraryType = LiteraryType;
  protected readonly headers = headers;
  protected readonly PublicationItemStatus = PublicationItemStatus;
}

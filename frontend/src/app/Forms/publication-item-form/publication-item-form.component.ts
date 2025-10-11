import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from "@angular/forms";
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
import { ToasterService } from '../../Services/toaster.service';

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
  publicationForm: FormGroup;
  journalForm: FormGroup;
  literaryForm: FormGroup;
  @Input() pubItemId: number;
  @Input() publicationId: number;
  publicationItem: PublicationItem;
  publication: Publication;

  constructor(public formBuilder: FormBuilder, public pubService: PublicationService, public router: Router, public location: Location, public toasterService: ToasterService) {
  }

  ngOnInit() {
    this.createForm();

    //Populate form on update
    if (this.pubItemId) {
      this.getPublicationItem();
      this.publicationForm.get("quantity").clearValidators();
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
    //PublicationItem fields
    this.publicationForm = this.formBuilder.group({
      publicationItemType: [, [Validators.required]],
      quantity: [1, [Validators.required, Validators.min(0), Validators.max(99999)]],
      edition: ['', [Validators.required, Validators.minLength(1), Validators.maxLength(25)]],
      format: ['', [Validators.required]],
      purchasePrice: ['', [Validators.required, Validators.min(0.1), Validators.max(99999)]],
      rentalRate: ['', [Validators.required, Validators.min(0.10), Validators.max(99999)]],
      publicationItemStatus: ['']
    });


    //No additional Book Fields
    //Journal Fields
    this.journalForm = this.formBuilder.group({
      issueDate: ['', [Validators.required]],
      issueNumber: ['', [Validators.required, Validators.minLength(1), Validators.maxLength(50)]],
      issueName: ['', [Validators.required, Validators.minLength(1), Validators.maxLength(100)]],
      volume: ['', [Validators.required, Validators.minLength(1), Validators.maxLength(100)]]
    });

    //Literary Piece
    this.literaryForm = this.formBuilder.group({
      literaryType: ['', [Validators.required]],
    });

  }

  fillForm() {
    if (this.publicationItem) {
      this.publicationForm.patchValue(this.publicationItem);
      this.journalForm.patchValue(this.publicationItem);
      this.literaryForm.patchValue(this.publicationItem);
    }
  }

  addPublicationItem() {
    if (this.publicationForm.valid) {
      let selectedItemType = this.publicationForm.get("publicationItemType").value;
      let newPublicationItem = null;

      if (selectedItemType == PublicationItemType.BOOK) {
        newPublicationItem = new Book(this.publicationForm.value);
      }

      if (selectedItemType == PublicationItemType.JOURNAL && this.journalForm.valid) {
        newPublicationItem = new Journal({ ...this.publicationForm.value, ...this.journalForm.value });
      }

      if (selectedItemType == PublicationItemType.LITERARY_PIECE && this.literaryForm.valid) {
        newPublicationItem = new LiteraryPiece({ ...this.publicationForm.value, ...this.literaryForm.value });
      }

      if (newPublicationItem) {
        if (this.pubItemId) {
          newPublicationItem.itemId = this.pubItemId;
          newPublicationItem.svgIcon = this.publicationItem.svgIcon;
        }
        newPublicationItem.publication = this.publication;
        this.createPublicationItem(newPublicationItem);
      } else {
        this.toasterService.message.set({ class: "error", "message": "Please complete form before submitting." });
      }

    } else {
      this.toasterService.message.set({ class: "error", "message": "Please complete form before submitting." });
    }
  }


  createPublicationItem(item: any) {
    this.pubService.newPublicationItem(item)
      .then(resp => {
        if (resp)
          // @ts-ignore 7053
          this.router.navigateByUrl(`/publication/${resp[0]["publication"]["publicationId"]}`)
      })
  }

  protected readonly PublicationItemType = PublicationItemType;
  protected readonly PublicationItemFormat = PublicationItemFormat;
  protected readonly LiteraryType = LiteraryType;
  protected readonly headers = headers;
  protected readonly PublicationItemStatus = PublicationItemStatus;
}

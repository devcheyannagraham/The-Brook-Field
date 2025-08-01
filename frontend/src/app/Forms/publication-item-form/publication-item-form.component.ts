import {Component, Input} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {PublicationService} from '../../Services/publication.service';
import {PublicationItemType} from '../../Enums/PublicationItemType';
import {PublicationItem} from '../../DTOs/Inventory/PublicationItem';
import {ItemType} from '../../Enums/ItemType';
import {PublicationItemStatus} from '../../Enums/PublicationItemStatus';
import {Journal} from '../../DTOs/Inventory/Journal';
import {LiteraryPiece} from '../../DTOs/Inventory/LiteraryPiece';
import {Book} from '../../DTOs/Inventory/Book';
import {Publication} from '../../DTOs/Inventory/Publication';

@Component({
  selector: 'publication-item-form',
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './publication-item-form.component.html',
  styleUrl: './publication-item-form.component.css'
})
export class PublicationItemFormComponent {
  publicationItemForm: FormGroup;
  publication: Publication;
  @Input() id: Number;

  constructor(public formBuilder: FormBuilder, public pubService: PublicationService) {
  }

  ngOnInit() {
    this.createForm();
    if (this.id) this.fillForm();
  }

  createForm() {
    this.publicationItemForm = this.formBuilder.group({

      //PublicationItem fields
      publicationType: ['BOOK'],
      quantity:[1],
      edition: [''],
      format: [''],
      purchasePrice: [''],
      rentalRate: [''],

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
    // Save data in service as cache

    // if (this.id) {
    //   this.pubService.getPublicationItemd(this.id)
    //     .subscribe(data => {
    //       for (let control in this.publicationItemForm.controls) {
    //         // @ts-ignore
    //         this.publicationItemForm.get(control).setValue(data[control])
    //       }
    //     })
    // }
  }

  addPublicationItem() {
    const formData = this.publicationItemForm.value;
    const publicationItem = new PublicationItem();
    publicationItem.publication = this.publication;

    for (let control in this.publicationItemForm.controls) {
      // @ts-ignore
      publicationItem[control] = this.publicationItemForm.get(control).value;
    }
    if (formData.publicationType === PublicationItemType.JOURNAL) {
      this.createPublicationItem(publicationItem as Journal);
    } else if (formData.publicationType === PublicationItemType.LITERARY_PIECE) {
      this.createPublicationItem(publicationItem as LiteraryPiece);
    } else {
      this.createPublicationItem(publicationItem as Book);
    }
  }

  createPublicationItem(item: any) {
    this.pubService.newPublicationItem(item)
  }

  protected readonly PublicationItemType = PublicationItemType;
}

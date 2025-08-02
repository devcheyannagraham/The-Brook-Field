import {Component, Input} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {PublicationService} from '../../Services/publication.service';
import {PublicationItemType} from '../../Enums/PublicationItemType';
import {PublicationItem} from '../../DTOs/Inventory/PublicationItem';
import {PublicationItemStatus} from '../../Enums/PublicationItemStatus';
import {Journal} from '../../DTOs/Inventory/Journal';
import {LiteraryPiece} from '../../DTOs/Inventory/LiteraryPiece';
import {Book} from '../../DTOs/Inventory/Book';
import {Publication} from '../../DTOs/Inventory/Publication';
import {PublicationItemFormat} from '../../Enums/PublicationItemFormat';
import {LiteraryType} from '../../Enums/LiteraryType';

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
  @Input() pubItemId: Number;
  @Input() publicationId: Number;
  publications: Publication[];

  constructor(public formBuilder: FormBuilder, public pubService: PublicationService) {
  }

  ngOnInit() {
    this.createForm();
    this.getPublications();
    if (this.pubItemId) {
      this.fillForm();
    }

    if(this.publicationId){
      this.publicationItemForm.get("publication").setValue(this.publicationId);
    }
  }

  getPublications() {
    this.pubService.getPublications().subscribe(data => {
      this.publications = data;
    });
  }

  createForm() {
    this.publicationItemForm = this.formBuilder.group({
      //PublicationItem fields
      publication: [],
      publicationItemType: [],
      quantity: [1],
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
    if (this.pubItemId) {
      this.pubService.getPublicationItemById(this.pubItemId)
        .subscribe(data => {
          for (let key of Object.keys(data)) {
            // @ts-ignore
            if (data[key] != null && this.publicationItemForm.contains(key)) {
              if (key === "publication") continue;
              // @ts-ignore
              this.publicationItemForm.get(key).setValue(data[key]);
            }
          }
          this.publicationItemForm.get("publication").setValue(data.publication.publicationId);
        });
    }
  }

  addPublicationItem() {
    const formData = this.publicationItemForm.value;
    let publicationItem = new PublicationItem();

    if (formData.publicationItemType === PublicationItemType.JOURNAL) {
      publicationItem = new Journal();
    } else if (formData.publicationItemType === PublicationItemType.LITERARY_PIECE) {
      publicationItem = new LiteraryPiece();
    } else if (formData.publicationItemType === PublicationItemType.BOOK) {
      publicationItem = new Book();
    }

    for (let key of Object.keys(publicationItem)) {
      if (key == "publication") continue;
      if (this.publicationItemForm.contains(key)) {
        // @ts-ignore
        publicationItem[key] = this.publicationItemForm.get(key).value;
      }
    }

    publicationItem.publication = new Publication();
    publicationItem.publication.publicationId = formData.publication;
    publicationItem.status = PublicationItemStatus.AVAILABLE;
    if (this.pubItemId) publicationItem.itemId = this.pubItemId;
    this.createPublicationItem(publicationItem);
  }

  createPublicationItem(item: any) {
    this.pubService.newPublicationItem(item)
      .subscribe(resp => console.log("RESPONSE: \n", resp))
  }

  protected readonly PublicationItemType = PublicationItemType;
  protected readonly PublicationItemFormat = PublicationItemFormat;
  protected readonly LiteraryType = LiteraryType;
}

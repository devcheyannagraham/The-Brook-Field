import {Component, Input, input} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule,} from '@angular/forms';
import {PublicationService} from '../../Services/publication.service';
import {Journal} from '../../DTOs/Inventory/Journal';
import {PublicationItemType} from '../../Enums/PublicationItemType';
import {PublicationItem} from '../../DTOs/Inventory/PublicationItem';
import {ItemType} from '../../Enums/ItemType';
import {Publication} from '../../DTOs/Inventory/Publication';
import {PublicationItemStatus} from '../../Enums/PublicationItemStatus';
import {LiteraryPiece} from '../../DTOs/Inventory/LiteraryPiece';
import {Book} from '../../DTOs/Inventory/Book';

@Component({
  selector: 'publication-form',
  imports: [ReactiveFormsModule],
  templateUrl: './publication-form.component.html',
  styleUrl: './publication-form.component.css',
})
export class PublicationFormComponent {
  publicationForm: FormGroup;
  @Input() id: Number;

  constructor(
    public formBuilder: FormBuilder,
    public pubService: PublicationService
  ) {
    if (this.id) console.log("ID: ", this.id)
    this.publicationForm = this.formBuilder.group({
      //Publciation fields
      title: [''],
      isbn: [''],
      datePublished: [''],
      genre: [''],
      publicationType: ['BOOK'],

      //PublicationItem fields
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

  ngOnInit() {
    if(this.id)this.fillForm();
  }

  fillForm() {
    if (this.id) {
      this.pubService.getPublicationById(this.id)
        .subscribe(data => {
          for (let control in this.publicationForm.controls) {
            // @ts-ignore
            this.publicationForm.get(control).setValue(data[control])
          }
        })
    }
  }

  addPublication() {
    const formData = this.publicationForm.value;

    //Publication
    const publication = new Publication();
    publication.datePublished = formData.datePublished;
    publication.isbn = formData.isbn;
    publication.genre = formData.genre;
    publication.title = formData.title;

    // create publicationItem
    const publicationItem = new PublicationItem();
    publicationItem.itemType = ItemType.PUBLICATION_ITEM;
    publicationItem.edition = formData.edtion;
    publicationItem.format = formData.format;
    publicationItem.publicationItemType = formData.publicationItemType;
    publicationItem.purchasePrice = formData.purchasePrice;
    publicationItem.rentalRate = formData.rentalRate;
    publicationItem.status = PublicationItemStatus.AVAILABLE;
    publicationItem.publication = publication;


    // cast to journal
    if (formData.publicationType === PublicationItemType.JOURNAL) {
      console.log('New Joural');
      const journal = publicationItem as Journal;
      journal.issueDate = formData.issueDate;
      journal.issueNumber = formData.issueNumber;
      journal.issueName = formData.issueName;
      journal.volume = formData.volume;
      this.createPublication(journal);

      // cast to Literary Piece
    } else if (formData.publicationType === PublicationItemType.LITERARY_PIECE) {
      console.log('New LP');
      const literaryPiece = publicationItem as LiteraryPiece;
      literaryPiece.literaryType = formData.literaryType;
      this.createPublication(literaryPiece);

      //cast to book
    } else {
      console.log('New Book');
      const book = publicationItem as Book;
      this.createPublication(book);
    }
  }

  createPublication(item: any) {
    this.pubService.newPublication(item)
      .subscribe(resp => {
        console.log("RESPONSE: ", resp);
      });
  }
}

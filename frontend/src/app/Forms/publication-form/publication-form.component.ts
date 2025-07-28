import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { PublicationService } from '../../Services/publication.service';
import { Book } from '../../DTOs/Inventory/Book';
import { Journal } from '../../DTOs/Inventory/Journal';
import { LiteraryPiece } from '../../DTOs/Inventory/LiteraryPiece';

@Component({
  selector: 'publication-form',
  imports: [ReactiveFormsModule],
  templateUrl: './publication-form.component.html',
  styleUrl: './publication-form.component.css',
})
export class PublicationFormComponent {
  publicationForm: FormGroup;
  constructor(
    public formBuilder: FormBuilder,
    public pubService: PublicationService
  ) {
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

  async addPublication() {
    const formData = this.publicationForm.value;
    const publicationType = formData.publicationType.toUpperCase();
    let item = null;

    if (publicationType.toUpperCase() === 'JOURNAL') {
      console.log('New Joural');
      item = new Journal(formData);
    } else if (publicationType === 'LITERARY_PIECE') {
      console.log('New LP');
      item = new LiteraryPiece(formData);
    } else {
      console.log('New Book');
      item = new Book(formData);
    }

    let response = await this.pubService.newPublication(item);
    console.log('response\n', response);
  }
}

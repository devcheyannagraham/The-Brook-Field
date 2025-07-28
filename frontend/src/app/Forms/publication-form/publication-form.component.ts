import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { PublicationService } from '../../Services/publication.service';
import { Book } from '../../DTOs/Inventory/Book';

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
      publicationType: ['book'],

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
    let formData = this.publicationForm.value;
    let book = new Book(formData);
    let response = await this.pubService.newPublication(book);
    console.log('response\n', response);
  }
}

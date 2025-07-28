import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { PublicationService } from '../../Services/publication.service';

@Component({
  selector: 'publication-form',
  imports: [ReactiveFormsModule],
  templateUrl: './publication-form.component.html',
  styleUrl: './publication-form.component.css',
})
export class PublicationFormComponent {
  publicationForm: FormGroup;
  publicationService: PublicationService;

  constructor(
    private formBuilder: FormBuilder,
    private pubService: PublicationService
  ) {
    this.publicationService = pubService;

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
    let response = await this.publicationService.newPublication(
      this.publicationForm.value
    );
    console.log('response', response);
  }
}

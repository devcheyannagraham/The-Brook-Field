import { Component, Input, input } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, } from '@angular/forms';
import { PublicationService } from '../../Services/publication.service';
import { Publication } from '../../DTOs/Inventory/Publication';
import { Genre } from '../../Enums/Genre';
import { Author } from '../../DTOs/Inventory/Author';
import { Router } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'publication-form',
  imports: [ReactiveFormsModule],
  templateUrl: './publication-form.component.html',
  styleUrl: './publication-form.component.css',
})
export class PublicationFormComponent {
  publicationForm: FormGroup;
  Genre = Genre;
  publication: Publication;
  author: Author;

  @Input() publicationId: number;

  constructor(public formBuilder: FormBuilder, public pubService: PublicationService, public router: Router, public location: Location) {
  }

  ngOnInit() {
    this.createForm();
    if (this.publicationId) this.getPublication();
  }

  getPublication() {
    this.pubService.getPublicationById(this.publicationId)
      .then(data => {
        if (data) {
          this.publication = data;
          this.author = data.author;
          this.fillForm();
        }
      });

  }

  createForm() {
    this.publicationForm = this.formBuilder.group({
      //Publciation fields
      publicationGroup: this.formBuilder.group({
        title: [''],
        isbn: [''],
        datePublished: [''],
        genre: ['']
      }),

      authorGroup: this.formBuilder.group({
        firstName: [''],
        lastName: ['']
      })
    });
  }

  fillForm() {
    if (this.publicationId) {
      this.publicationForm.get("publicationGroup").patchValue(this.publication);
      this.publicationForm.get("authorGroup").patchValue(this.author);
    }
  }


  addPublication() {
    const publication = new Publication(this.publicationForm.get("publicationGroup").value);
    publication.author = new Author(this.publicationForm.get("authorGroup").value);
    publication.publicationId = this.publicationId || null;
    publication.author.id = this.author?.id || null;
    //add icon if pub already exists
    if(this.publication) publication.svgIcon = this.publication.svgIcon;

    this.createPublication(publication);
  }

  createPublication(pub: any) {
    this.pubService.newPublication(pub)
      .then(resp => {
        // @ts-ignore
        if (resp["publicationId"]) this.router.navigateByUrl(`/publication/${resp["publicationId"]}`);
      });
  }

}

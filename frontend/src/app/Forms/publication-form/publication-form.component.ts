import {Component, Input, input} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule,} from '@angular/forms';
import {PublicationService} from '../../Services/publication.service';
import {Publication} from '../../DTOs/Inventory/Publication';
import {Book} from '../../DTOs/Inventory/Book';
import {Genre} from '../../Enums/Genre';
import {Author} from '../../DTOs/Inventory/Author';

@Component({
  selector: 'publication-form',
  imports: [ReactiveFormsModule],
  templateUrl: './publication-form.component.html',
  styleUrl: './publication-form.component.css',
})
export class PublicationFormComponent {
  publicationForm: FormGroup;
  Genre = Genre;

  @Input() publicationId: Number;

  constructor(public formBuilder: FormBuilder, public pubService: PublicationService) {
  }

  ngOnInit() {
    this.createForm();
    if (this.publicationId) this.fillForm();
  }

  createForm() {
    this.publicationForm = this.formBuilder.group({
      //Publciation fields
      title: [''],
      isbn: [''],
      datePublished: [''],
      genre: [''],
      firstName: [''],
      lastName: ['']
    });
  }

  fillForm() {
    if (this.publicationId) {
      this.pubService.getPublicationById(this.publicationId)
        .subscribe(data => {
          for (let key of Object.keys(data)) {
            if (key != null && this.publicationForm.contains(key)) {
              // @ts-ignore
              this.publicationForm.get(key).setValue(data[key])
            }
            this.publicationForm.get("firstName").setValue(data["author"]["firstName"])
            this.publicationForm.get("lastName").setValue(data["author"]["lastName"])
          }
        })
    }
  }

  addPublication() {
    const publication = new Publication();
    publication.author = new Author();

    for (let control in this.publicationForm.controls) {
      if (control === "firstName") publication.author.firstName = this.publicationForm.get(control).value;
      if (control === "lastName") publication.author.lastName = this.publicationForm.get(control).value;
      else { // @ts-ignore
        publication[control] = this.publicationForm.get(control).value;
      }
    }
    this.createPublication(publication);
  }

  createPublication(pub: any) {
    this.pubService.newPublication(pub)
      .subscribe(resp => {
        console.log("RESPONSE: ", resp);
      });
  }

}

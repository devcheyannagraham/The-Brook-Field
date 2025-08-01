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
import {Genre} from '../../Enums/Genre';

@Component({
  selector: 'publication-form',
  imports: [ReactiveFormsModule],
  templateUrl: './publication-form.component.html',
  styleUrl: './publication-form.component.css',
})
export class PublicationFormComponent {
  publicationForm: FormGroup;
  @Input() id: Number;

  constructor(public formBuilder: FormBuilder, public pubService: PublicationService) {
  }

  ngOnInit() {
    this.createForm();
    if (this.id) this.fillForm();
  }

  createForm() {
    this.publicationForm = this.formBuilder.group({
      //Publciation fields
      title: [''],
      isbn: [''],
      datePublished: [''],
      genre: [''],
    });
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
    for (let control in this.publicationForm.controls) {
      // @ts-ignore
      publication[control] = this.publicationForm.get(control).value;
    }
    this.createPublication(publication)
  }

  createPublication(pub: any) {
    this.pubService.newPublication(pub)
      .subscribe(resp => {
        console.log("RESPONSE: ", resp);
      });
  }

  protected readonly Genre = Genre;
}

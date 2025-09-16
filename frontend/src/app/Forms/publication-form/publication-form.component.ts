import {Component, Input, input} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule,} from '@angular/forms';
import {PublicationService} from '../../Services/publication.service';
import {Publication} from '../../DTOs/Inventory/Publication';
import {Book} from '../../DTOs/Inventory/Book';
import {Genre} from '../../Enums/Genre';
import {Author} from '../../DTOs/Inventory/Author';
import {Router} from '@angular/router';
import {platformBrowser} from '@angular/platform-browser';

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

  constructor(public formBuilder: FormBuilder, public pubService: PublicationService, public router: Router) {
  }

  ngOnInit() {
    this.createForm();
    if (this.publicationId) this.getPublication();
  }

  getPublication() {
    this.pubService.getPublicationById(this.publicationId)
      .subscribe(data => {
        this.publication = data;
        this.author = data.author;
        this.fillForm();
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

    // for (let control in this.publicationForm.controls) {
    //   if (control === "firstName") publication.author.firstName = this.publicationForm.get(control).value;
    //   if (control === "lastName") publication.author.lastName = this.publicationForm.get(control).value;
    //   else { // @ts-ignore
    //     publication[control] = this.publicationForm.get(control).value;
    //   }
    // }
    publication.publicationId = this.publicationId || null;
    publication.author.id = this.author?.id || null;
    console.log("NEW/UPDATED PUB", publication);

    this.createPublication(publication);
  }

  createPublication(pub: any) {
    this.pubService.newPublication(pub)
      .subscribe(resp => {
        console.log("RESPONSE: ", resp);
        // @ts-ignore
        if (resp["publicationId"]) this.router.navigateByUrl(`/publication/${resp["publicationId"]}`);
      });
  }
  goBack(){
    if(this.publicationId) this.router.navigateByUrl(`/publication/${this.publicationId}`)
    else this.router.navigateByUrl("/publications")
  }

}

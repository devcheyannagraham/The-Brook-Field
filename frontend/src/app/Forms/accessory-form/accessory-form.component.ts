import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'accessory-form',
  imports: [ReactiveFormsModule],
  templateUrl: './accessory-form.component.html',
  styleUrl: './accessory-form.component.css',
})
export class AccessoryFormComponent {
  accessoryForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.accessoryForm = this.formBuilder.group({
      accessoryName: [''],
      accessoryType: [''],
      price: [''],
      quantity: [1],
    });
  }

  addAccessory() {
    alert('accessoryAdded');
  }
}

import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AccessoryService } from '../../Services/accessory.service';
import { Accessory } from '../../DTOs/Accessory/Accessory';
import { AccessoryType } from '../../Enums/AccessoryType';
import { Router } from '@angular/router';

@Component({
  selector: 'accessory-form',
  imports: [ReactiveFormsModule, FormsModule],
  templateUrl: './accessory-form.component.html',
  styleUrl: './accessory-form.component.css',
})
export class AccessoryFormComponent {
  accessoryForm: FormGroup;
  AccessoryType = AccessoryType;
  accessory: Accessory;
  @Input() accessoryId: number;


  constructor(private formBuilder: FormBuilder, public accessoryService: AccessoryService, public router: Router) {
  }

  ngOnInit() {
    this.createForm();
    if (this.accessoryId) this.getAccessory();
  }

  createForm() {
    this.accessoryForm = this.formBuilder.group({
      accessoryName: [''],
      accessoryType: [''],
      price: [''],
      quantity: [1],
      accessoryId: []
    });
  }

  getAccessory() {
    if (this.accessoryId) {
      this.accessoryService.getAccessoryById(this.accessoryId)
        .then(acc => {
          if (acc) {
            this.accessory = acc;
            this.fillForm();
          }
        })
    }
  }

  fillForm() {
    if (this.accessory) {
      this.accessoryForm.patchValue(this.accessory);
    }
  }


  addAccessory() {
    let accessory = new Accessory(this.accessoryForm.value);
    accessory.svgIcon = this.accessory.svgIcon;
    this.saveAccessory(accessory);
  }

  saveAccessory(accessory: Accessory) {
    this.accessoryService.newAccessory(accessory)
      .then(data => {
        // @ts-ignore
        if (data) this.router.navigateByUrl(`/accessory/${data["accessoryId"]}`)

      });
  }


}

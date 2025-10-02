import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AccessoryService } from '../../Services/accessory.service';
import { Accessory } from '../../DTOs/Accessory/Accessory';
import { AccessoryType } from '../../Enums/AccessoryType';
import { Router } from '@angular/router';
import { Validators } from '@angular/forms';
import { ToasterService } from '../../Services/toaster.service';

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


  constructor(private formBuilder: FormBuilder, public accessoryService: AccessoryService, public router: Router, public toasterService:ToasterService) {
  }

  ngOnInit() {
    this.createForm();
    if (this.accessoryId) this.getAccessory();
  }

  createForm() {
    this.accessoryForm = this.formBuilder.group({
      accessoryName: ['',[Validators.required, Validators.minLength(5), Validators.maxLength(25)]],
      accessoryType: ['',[Validators.required]],
      price: ['',[Validators.required, Validators.min(0.1), Validators.max(99999)]],
      quantity: [1,[Validators.required, Validators.min(0), Validators.max(99999)]],
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
    if (this.accessoryForm.valid) {
      let accessory = new Accessory(this.accessoryForm.value);
      accessory.svgIcon = this.accessory?.svgIcon;
      this.saveAccessory(accessory);
    }
    else {
      this.toasterService.message.set({class:"error", message:"Please complete the form before submitting"})

    }
  }

  saveAccessory(accessory: Accessory) {
    this.accessoryService.newAccessory(accessory)
      .then(data => {
        // @ts-ignore
        if (data) this.router.navigateByUrl(`/accessory/${data["accessoryId"]}`)

      });
  }


}

import {Component, Input} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {AccessoryService} from '../../Services/accessory.service';
import {Accessory} from '../../DTOs/Accessory/Accessory';
import {AccessoryType} from '../../Enums/AccessoryType';

@Component({
  selector: 'accessory-form',
  imports: [ReactiveFormsModule],
  templateUrl: './accessory-form.component.html',
  styleUrl: './accessory-form.component.css',
})
export class AccessoryFormComponent {
  accessoryForm: FormGroup;
  accessories: Accessory[]
  @Input() accessId: Number;

  constructor(private formBuilder: FormBuilder, public accessoryService: AccessoryService) {
  }

  ngOnInit() {
    this.createForm();
    this.getAccessories();
    if (this.accessId) this.fillForm();
  }

  createForm() {
    this.accessoryForm = this.formBuilder.group({
      accessoryName: [''],
      accessoryType: [''],
      price: [''],
      quantity: [1],
    });
  }

  getAccessories() {
    this.accessoryService.getAccessories()
      .subscribe(data => {
        this.accessories = data;
      });
  }

  fillForm() {
    console.log("IMPLEMENT FILL FORM");
  }

  addAccessory() {
    let accessory = new Accessory();
    for (let key of Object.keys(accessory)) {
      // @ts-ignore
      if(this.accessoryForm.contains(key))accessory[key] = this.accessoryForm.get(key).value;
    }
    this.saveAccessory(accessory);
  }

  saveAccessory(accessory: Accessory) {
    this.accessoryService.newAccessory(accessory)
      .subscribe(data => {
        console.log(data);
      });
  }


  protected readonly AccessoryType = AccessoryType;
}

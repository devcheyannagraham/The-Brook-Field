import {Component, Input} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AccessoryService} from '../../Services/accessory.service';
import {Accessory} from '../../DTOs/Accessory/Accessory';
import {AccessoryType} from '../../Enums/AccessoryType';
import {Router} from '@angular/router';

@Component({
  selector: 'accessory-form',
  imports: [ReactiveFormsModule, FormsModule],
  templateUrl: './accessory-form.component.html',
  styleUrl: './accessory-form.component.css',
})
export class AccessoryFormComponent {
  accessoryForm: FormGroup;
  protected readonly AccessoryType = AccessoryType;

  @Input() accessoryId: number;

  constructor(private formBuilder: FormBuilder, public accessoryService: AccessoryService, public router: Router) {
  }

  ngOnInit() {
    this.createForm();
    if (this.accessoryId) this.fillForm();
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

  fillForm() {
    if (this.accessoryId) {
      this.accessoryService.getAccessory(this.accessoryId)
        .subscribe(data => {
          for (let key of Object.keys(data)) {
            if (key != null && this.accessoryForm.contains(key)) {
              // @ts-ignore
              this.accessoryForm.get(key).setValue(data[key]);
            }
          }
        });
    }
  }


  addAccessory() {
    let accessory = new Accessory();
    for (let key of Object.keys(accessory)) {
      // @ts-ignore
      if (this.accessoryForm.contains(key)) accessory[key] = this.accessoryForm.get(key).value;
    }
    this.saveAccessory(accessory);
  }

  saveAccessory(accessory: Accessory) {
    this.accessoryService.newAccessory(accessory)
      .subscribe(data => {
        // @ts-ignore
        this.router.navigateByUrl(`/accessory/${data["accessoryId"]}`)

      });
  }


}

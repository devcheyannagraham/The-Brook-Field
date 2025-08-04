import { Component } from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {OrderService} from '../../Services/order.service';

@Component({
  selector: 'order-form',
  imports: [],
  templateUrl: './order-form.component.html',
  styleUrl: './order-form.component.css'
})
export class OrderFormComponent {

  constructor(public orderSerivce: OrderService, public formBuilder: FormBuilder){}

}

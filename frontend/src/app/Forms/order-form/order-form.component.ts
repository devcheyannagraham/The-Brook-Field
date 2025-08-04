import { Component } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {OrderService} from '../../Services/order.service';

@Component({
  selector: 'order-form',
  imports: [],
  templateUrl: './order-form.component.html',
  styleUrl: './order-form.component.css'
})
export class OrderFormComponent {
  orderForm: FormGroup;
  customerForm: FormGroup;

  constructor(public orderSerivce: OrderService, public formBuilder: FormBuilder){}

  ngOnInit(){
    this.createForm();
  }

  createForm(){
    this.customerForm = this.formBuilder.group({
      firstName:[],
      lastName:[],
      streetAddress: [],
      city: [],
      state:[],
      zip:[],
      country:[],
      email:[],
      phoneNumber:[]
    })

    this.orderForm = this.formBuilder.group({
      orderDate:[],
      orderTotal: []


    });
  }
}

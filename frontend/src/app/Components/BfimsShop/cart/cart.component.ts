import { Component } from '@angular/core';
import { ShopService } from '../../../Services/shop.service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'cart',
  imports: [RouterLink],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent {
  constructor(public shopService:ShopService){

  }

}

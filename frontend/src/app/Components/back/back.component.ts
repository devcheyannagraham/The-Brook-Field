import { Component } from '@angular/core';
import { Location } from '@angular/common';

@Component({
  selector: 'back',
  imports: [],
  templateUrl: './back.component.html',
  styleUrl: './back.component.css'
})
export class BackComponent {

  constructor(public location:Location){}

  goBack(){
    this.location.back();
  }

}

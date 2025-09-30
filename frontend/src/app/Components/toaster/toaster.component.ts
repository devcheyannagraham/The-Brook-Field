import { Component, effect } from '@angular/core';
import { ToasterService } from '../../Services/toaster.service';

@Component({
  selector: 'toaster',
  imports: [],
  templateUrl: './toaster.component.html',
  styleUrl: './toaster.component.css'
})
export class ToasterComponent {
  showMessage = false;


  constructor(public toasterService: ToasterService) {
    effect(()=>{
      this.toasterService.message();
      this.startTimer();
    })
  }

  startTimer() {
    this.showMessage = true;
    setTimeout(() => {
      this.showMessage = false;
    }, 5000);
  }

}

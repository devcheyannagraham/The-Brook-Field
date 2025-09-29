import { Component, Input } from '@angular/core';
import { SVGIcon } from '../../DTOs/SVGIcon';

@Component({
  selector: 'svgicon',
  imports: [],
  templateUrl: './svgicon.component.html',
  styleUrl: './svgicon.component.css'
})
export class SVGIconComponent {
  @Input() icon:SVGIcon;
  @Input() className:string;

}

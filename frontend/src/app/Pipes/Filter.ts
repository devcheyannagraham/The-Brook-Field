import {Pipe, PipeTransform} from '@angular/core';
import {Journal} from '../DTOs/Inventory/Journal';
import {Book} from '../DTOs/Inventory/Book';
import {LiteraryPiece} from '../DTOs/Inventory/LiteraryPiece';
import {PublicationItem} from '../DTOs/Inventory/PublicationItem';

@Pipe({
  name: 'filter'
})
export class Filter implements PipeTransform {
  transform(value: any, fn: Function) {
    return value.filter(fn());

  }
}

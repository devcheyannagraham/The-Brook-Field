import {Pipe, PipeTransform} from '@angular/core';
import {Journal} from '../DTOs/Inventory/Journal';
import {Book} from '../DTOs/Inventory/Book';
import {LiteraryPiece} from '../DTOs/Inventory/LiteraryPiece';
import {PublicationItem} from '../DTOs/Inventory/PublicationItem';

@Pipe({
  name: 'cast'
})
export class TypeCast implements PipeTransform
{
  transform(value: any) {
    console.log("TRANSFORMING VALUE:", value, value instanceof PublicationItem)
    if(value instanceof Journal) return value as Journal;
    if (value instanceof Book) return value as Book;
    if (value instanceof LiteraryPiece) return value as LiteraryPiece;
    return value;
  }

}

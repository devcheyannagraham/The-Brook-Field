import {Pipe, PipeTransform} from '@angular/core';
import {Journal} from '../DTOs/Inventory/Journal';
import {Book} from '../DTOs/Inventory/Book';
import {LiteraryPiece} from '../DTOs/Inventory/LiteraryPiece';
import {PublicationItem} from '../DTOs/Inventory/PublicationItem';
import {Publication} from '../DTOs/Inventory/Publication';
import {AccessoryItem} from '../DTOs/Accessory/AccessoryItem';
import {Accessory} from '../DTOs/Accessory/Accessory';

@Pipe({
  name: 'cast'
})
export class TypeCast implements PipeTransform
{
  transform(value: any) {
    if(value instanceof Journal) return value as Journal;
    if (value instanceof Book) return value as Book;
    if (value instanceof LiteraryPiece) return value as LiteraryPiece;
    if (value instanceof PublicationItem) return value as PublicationItem
    if (value instanceof Accessory) return value as Accessory
    if (value instanceof AccessoryItem) return value as AccessoryItem
    return value;
  }

}

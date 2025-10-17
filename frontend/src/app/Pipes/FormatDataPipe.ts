import {inject, Pipe, PipeTransform} from '@angular/core';
import {CurrencyPipe, DatePipe} from '@angular/common';

@Pipe({
  name: 'formatDataPipe',
})
export class FormatDataPipe implements PipeTransform {

  constructor(private currencyPipe: CurrencyPipe, private datePipe: DatePipe) {
  }

  transform(value: any, map: Map<any, any>, header: any) {
    let dataTypes = map.get(header);
    if (value && dataTypes.includes("currency"))
      return this.currencyPipe.transform(value);

    if (value && dataTypes.includes("date"))
      return this.datePipe.transform(value);
    return value;
  }
}

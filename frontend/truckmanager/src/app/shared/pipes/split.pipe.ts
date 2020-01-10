import { Pipe, PipeTransform  } from '@angular/core';

@Pipe({
  name: 'split'
})
export class SplitPipe implements PipeTransform {
  
  transform (input: any, separator: string = '|', limit?: number): any {
    if (!input) {
      return "";
    }
    if (!separator) {
      separator = '|';
    }
    return input.split(separator, limit);
  }
}
import { Pipe, PipeTransform, NgModule } from '@angular/core';

@Pipe({
  name: 'join',
})
export class JoinPipe implements PipeTransform {
  transform(input: any, character: string = ''): any {
    if (!Array.isArray(input)) {
      return '';
    }

    if (!character) {
      character = '';
    }

    return input.join(character);
  }
}
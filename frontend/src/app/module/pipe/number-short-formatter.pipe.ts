import {Pipe, PipeTransform} from '@angular/core';

@Pipe({name: 'numberShortFormatterPipe'})
export class NumberShortFormatterPipe implements PipeTransform {
    transform(num: number): string {
        return num <= 9 ? `${num}` : '9+';
    }
}

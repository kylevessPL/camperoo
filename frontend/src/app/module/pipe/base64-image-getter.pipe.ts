import {Pipe, PipeTransform} from '@angular/core';
import {DomSanitizer} from '@angular/platform-browser';
import {Image} from '../models/image';

@Pipe({name: 'base64ImageGetterPipe'})
export class Base64ImageGetterPipe implements PipeTransform {
    constructor(private domSanitizer: DomSanitizer) {
    }

    transform(base64Image: Image) {
        const {content, contentType} = base64Image;
        return this.domSanitizer.bypassSecurityTrustResourceUrl(`data:${contentType};base64,${content}`);
    }
}

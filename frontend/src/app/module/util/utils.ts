import {Page} from '../models/page';
import * as contentDispositionUtil from 'content-disposition';
import {HttpHeaders} from '@angular/common/http';
import {Headers} from 'http-constants-ts';

export default class Utils {
    static getDateDiffMillis = (d1: Date, d2: Date) => Math.abs(d2.getTime() - d1.getTime());
    static wrapToObject = <T extends Page<T>>(result: T[]) => {
        return {
            content: result
        } as T;
    };

    static createBlob = (content: any, headers: HttpHeaders) => {
        const contentDisposition = headers.get(Headers.CONTENT_DISPOSITION);
        const contentType = headers.get(Headers.CONTENT_TYPE);
        const a = document.createElement('a');
        const file = new Blob([content], {type: contentType});
        a.href = URL.createObjectURL(file);
        a.download = contentDispositionUtil
            .parse(contentDisposition)
            .parameters['filename'];
        a.click();
    };
}

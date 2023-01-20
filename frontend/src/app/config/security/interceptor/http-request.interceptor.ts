import {HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {GlobalService} from '../../../module/service/global.service';
import {Headers} from 'http-constants-ts';

@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {
    constructor(private globalService: GlobalService) {
    }

    intercept = (request: HttpRequest<any>, next: HttpHandler) => next.handle(request.clone({
        headers: this.createHeaders(request),
        withCredentials: true
    }));

    private createHeaders(request: HttpRequest<any>): HttpHeaders {
        const locale = this.globalService.locale.getValue();
        return locale
            ? request.headers.set(Headers.ACCEPT_LANGUAGE, locale)
            : request.headers;
    }
}

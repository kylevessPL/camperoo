import {HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';

@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {
    intercept = (request: HttpRequest<any>, next: HttpHandler) => next.handle(request.clone({
        withCredentials: true
    }));
}

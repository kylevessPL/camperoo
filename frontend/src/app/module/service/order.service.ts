import {Injectable} from '@angular/core';
import {PageMeta} from '../models/page-meta';
import {EncodeUriParamService} from './encode-uri-param.service';
import {environment} from '../../../environments/environment';
import {restUrl} from '../../../environments/rest-url';
import {AuthService} from '../../config/security/auth.service';
import {Order} from '../models/order';

@Injectable({providedIn: 'root'})
export class OrderService {
    constructor(private authService: AuthService, private httpClient: EncodeUriParamService) {
    }

    public getAllOrders = (pageMeta?: PageMeta) => this.authService.isAdmin()
        ? this.httpClient.getPage<Order>(`${environment.baseUrl}/${restUrl.ordersBase}`, pageMeta)
        : this.httpClient.getPage<Order>(`${environment.baseUrl}/${restUrl.ordersBase}/${restUrl.self}`, pageMeta);
}

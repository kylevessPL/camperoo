import {Injectable} from '@angular/core';
import {PageMeta} from '../models/page-meta';
import {EncodeUriParamService} from './encode-uri-param.service';
import {environment} from '../../../environments/environment';
import {restUrl} from '../../../environments/rest-url';
import {Payment} from '../models/payment';
import {AuthService} from '../../config/security/auth.service';

@Injectable({providedIn: 'root'})
export class PaymentService {
    constructor(private authService: AuthService, private httpClient: EncodeUriParamService) {
    }

    public getAllPayments = (pageMeta?: PageMeta) => this.authService.isAdmin()
        ? this.httpClient.getPage<Payment>(`${environment.baseUrl}/${restUrl.paymentsBase}`, pageMeta)
        : this.httpClient.getPage<Payment>(`${environment.baseUrl}/${restUrl.paymentsBase}/${restUrl.self}`, pageMeta);
}

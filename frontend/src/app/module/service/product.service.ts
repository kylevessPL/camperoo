import {Injectable} from '@angular/core';
import {PageMeta} from '../models/page-meta';
import {EncodeUriParamService} from './encode-uri-param.service';
import {environment} from '../../../environments/environment';
import {restUrl} from '../../../environments/rest-url';
import {AuthService} from '../../config/security/auth.service';
import {Product} from '../models/product';

@Injectable({providedIn: 'root'})
export class ProductService {
    constructor(private authService: AuthService, private httpClient: EncodeUriParamService) {
    }

    public getAllProducts = (pageMeta?: PageMeta) =>
        this.httpClient.getPage<Product>(`${environment.baseUrl}/${restUrl.productsBase}`, pageMeta);
}

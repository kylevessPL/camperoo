import {Injectable} from '@angular/core';
import {PageMeta} from '../models/page-meta';
import {User} from '../models/user';
import {EncodeUriParamService} from './encode-uri-param.service';
import {environment} from '../../../environments/environment';
import {restUrl} from '../../../environments/rest-url';

@Injectable({providedIn: 'root'})
export class UserService {
    constructor(private httpClient: EncodeUriParamService) {
    }

    public getAllUsers = (pageMeta: PageMeta) => this.httpClient
        .getPage<User[]>(`${environment.baseUrl}/${restUrl.usersBase}`, pageMeta);
}

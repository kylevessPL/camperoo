import {Injectable} from '@angular/core';
import {environment} from '../../../environments/environment';
import {restUrl} from '../../../environments/rest-url';
import Utils from '../util/utils';
import {HttpClient} from '@angular/common/http';

@Injectable({providedIn: 'root'})
export class BranchService {
    constructor(private httpClient: HttpClient) {
    }

    public getFile = (uuid: string) => this.httpClient
        .get(`${environment.baseUrl}/${restUrl.filesBase}/${uuid}`, {
            responseType: 'ArrayBuffer' as 'json',
            observe: 'response'
        }).subscribe(response => {
            Utils.downloadBlob(response.body, response.headers);
        });
}

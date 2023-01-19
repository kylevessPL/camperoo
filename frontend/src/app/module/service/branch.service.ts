import {Injectable} from '@angular/core';
import {environment} from '../../../environments/environment';
import {restUrl} from '../../../environments/rest-url';
import {CompanyBranch} from '../models/company-branch';
import {map} from 'rxjs/operators';
import Utils from '../util/utils';
import {HttpClient} from '@angular/common/http';

@Injectable({providedIn: 'root'})
export class BranchService {
    constructor(private httpClient: HttpClient) {
    }

    public getAllCompanyBranches = () => this.httpClient
        .get<CompanyBranch[]>(`${environment.baseUrl}/${restUrl.branchesBase}`)
        .pipe(map(e => Utils.wrapToObject(e)));
}

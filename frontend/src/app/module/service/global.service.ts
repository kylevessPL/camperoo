import {Injectable} from '@angular/core';
import {BehaviorSubject, Subject} from 'rxjs';
import {MatSnackBar} from '@angular/material/snack-bar';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {restUrl} from '../../../environments/rest-url';
import {share} from 'rxjs/operators';
import {Locale} from '../models/locale';

@Injectable({
    providedIn: 'root'
})
export class GlobalService {
    public httpErrorStatus = new BehaviorSubject<HttpErrorResponse>(null);
    public cartItemsNumber = new Subject<number>();

    constructor(private snackBar: MatSnackBar, private httpClient: HttpClient) {
    }

    public getAllLocales = () => this.httpClient
        .get<Locale[]>(`${environment.baseUrl}/${restUrl.globalBase}/${restUrl.locales}`)
        .pipe(share());

    public showDataFetchErrorDialog = () => this.snackBar.open('Sorry probably something went wrong. Please try again later.',
        'Close',
        {
            duration: undefined
        });
}

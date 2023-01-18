import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {environment} from 'src/environments/environment';
import {UserStorage} from '../../module/storage/user.storage';
import {restUrl} from '../../../environments/rest-url';
import {RegisterData} from '../../module/models/register-data';
import {LoginData} from '../../module/models/login-data';
import {LoginResult} from '../../module/models/login-result';
import {Subscription, timer} from 'rxjs';
import Utils from '../../module/util/utils';

@Injectable({providedIn: 'root'})
export class AuthService {
    constructor(private userStorage: UserStorage, private httpClient: HttpClient, private router: Router) {
    }

    private _refreshTokenTask: Subscription;

    public login = (data: LoginData) => this.httpClient
        .post<LoginResult>(`${environment.baseUrl}/${restUrl.authBase}`, data)
        .subscribe(result => {
            this.refreshToken(result.expirationTime).then(() => {
            });
            this.userStorage.setRoles(result.roles);
            this.router.navigate([`/dashboard`]).then(() => {
            });
        });

    public register = (data: RegisterData) => this.httpClient
        .post(`${environment.baseUrl}/${restUrl.usersBase}`, data)
        .subscribe(() => {
            this.router.navigate([`/register`], {
                skipLocationChange: true,
                state: {
                    success: true
                }
            }).then(() => {
            });
        });

    public confirm = (token: string) => this.httpClient
        .post(`${environment.baseUrl}/${restUrl.usersBase}/${restUrl.confirmation}`, {code: token})
        .subscribe(() => {
            this.router.navigate([`/login`], {
                state: {
                    verified: true
                }
            }).then(() => {
            });
        }, () => {
            this.router.navigate([this.router.url], {
                state: {
                    valid: false
                }
            }).then(() => {
            });
        });

    public refreshToken = (expirationEpochSeconds = 0) => new Promise(() => {
        const fireTime = expirationEpochSeconds !== 0
            ? Utils.getDateDiffMillis(new Date(), new Date((expirationEpochSeconds - 60) * 1000))
            : 0;
        this._refreshTokenTask = timer(fireTime).subscribe(() => {
            this.httpClient.post<LoginResult>(`${environment.baseUrl}/${restUrl.authBase}/${restUrl.refreshToken}`, null)
                .subscribe(result => {
                    this.userStorage.setRoles(result.roles);
                    this.refreshToken(result.expirationTime).then(() => {
                    });
                }, () => this.clearUserStorage());
        });
    });

    public logout() {
        if (this._refreshTokenTask != null) {
            this._refreshTokenTask.unsubscribe();
        }
        this.httpClient
            .post(`${environment.baseUrl}/${restUrl.authBase}/${restUrl.logout}`, null)
            .subscribe(() => {
                this.clearUserStorage();
                this.router.navigateByUrl('/').then(() => {
                });
            });
    }

    private clearUserStorage() {
        this.userStorage.removeLocale();
        this.userStorage.removeRoles();
    }

    public hasRole = (role: string) => this.userStorage.isRolesSet() ? this.userStorage.getRoles().includes(role) : false;

    public hasAnyRole = () => this.userStorage.isRolesSet();

    public isAdmin = () => this.hasRole('ADMIN');

    public isCustomer = () => this.hasRole('CUSTOMER');

    public getLanguage = () => this.userStorage.getLocale();
}

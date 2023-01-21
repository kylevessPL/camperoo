import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {environment} from 'src/environments/environment';
import {GlobalStorage} from '../../module/storage/global.storage.service';
import {restUrl} from '../../../environments/rest-url';
import {RegisterData} from '../../module/models/register-data';
import {LoginData} from '../../module/models/login-data';
import {LoginResult} from '../../module/models/login-result';
import {Subscription, timer} from 'rxjs';
import Utils from '../../module/util/utils';
import {GlobalService} from '../../module/service/global.service';

@Injectable({providedIn: 'root'})
export class AuthService {
    private _refreshTokenTask: Subscription;

    constructor(private globalStorage: GlobalStorage,
                private globalService: GlobalService,
                private httpClient: HttpClient,
                private router: Router,
                private route: ActivatedRoute) {
    }

    public login = (data: LoginData) => this.httpClient
        .post<LoginResult>(`${environment.baseUrl}/${restUrl.authBase}`, data)
        .subscribe(result => this.loginInternal(result));

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
        .subscribe({
            next: () => this.router.navigate([`/login`], {
                state: {
                    verified: true
                }
            }).then(() => {
            }),
            error: () => this.router.navigate([this.router.url], {
                state: {
                    valid: false
                }
            }).then(() => {
            })
        });

    public refreshToken = (expirationEpochSeconds = 0) => new Promise(() => {
        const fireTime = expirationEpochSeconds !== 0
            ? Utils.getDateDiffMillis(new Date(), new Date((expirationEpochSeconds - 60) * 1000))
            : 0;
        this._refreshTokenTask = timer(fireTime).subscribe(() => {
            this.httpClient.post<LoginResult>(`${environment.baseUrl}/${restUrl.authBase}/${restUrl.refreshToken}`, null)
                .subscribe({
                    next: result => {
                        this.globalStorage.setRoles(result.roles);
                        this.refreshToken(result.expirationTime).then(() => {
                        });
                    },
                    error: () => this.logoutInternal(this.router.routerState.snapshot.url)
                });
        });
    });

    public logout() {
        if (this._refreshTokenTask != null) {
            this._refreshTokenTask.unsubscribe();
        }
        this.httpClient
            .post(`${environment.baseUrl}/${restUrl.authBase}/${restUrl.logout}`, null)
            .subscribe(() => this.logoutInternal());
    }

    public hasRole = (role: string) => this.globalStorage.isRolesSet() ? this.globalStorage.getRoles().includes(role) : false;

    public hasAnyRole = () => this.globalStorage.isRolesSet();

    public isAdmin = () => this.hasRole('ADMIN');

    public isCustomer = () => this.hasRole('CUSTOMER');

    private loginInternal(result: LoginResult) {
        this.refreshToken(result.expirationTime).then(() => {
        });
        this.globalStorage.setUserId(result.userId);
        this.globalStorage.setRoles(result.roles);
        this.setInitialCartItemsNumber();
        this.redirectAfterLogin();
    }

    private logoutInternal(redirectUrl?: string) {
        this.globalStorage.removeUserId();
        this.globalStorage.removeRoles();
        this.router.navigate([`/`], {state: {'redirectUrl': redirectUrl}}).then(() => {
        });
    }

    private redirectAfterLogin() {
        const redirectUrl: string = window.history.state['redirectUrl'];
        if (redirectUrl && redirectUrl.includes('/dashboard')) {
            this.router
                .navigateByUrl(redirectUrl)
                .catch(() => this.router.navigate(['/dashboard']));
        } else {
            this.router.navigate(['/dashboard']).then(() => {
            });
        }
    }

    private setInitialCartItemsNumber() {
        const itemsNumber = this.globalStorage.getUserCart().products.size;
        this.globalService.cartItemsNumber.next(itemsNumber);
    }
}

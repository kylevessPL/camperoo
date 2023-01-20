import {ChangeDetectorRef, Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from './config/security/auth.service';
import {GlobalService} from './module/service/global.service';
import {Locale} from './module/models/locale';
import {Country} from '@angular-material-extensions/select-country';
import {MediaMatcher} from '@angular/cdk/layout';
import {GlobalStorage} from './module/storage/global.storage.service';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy {
    title = 'Camperoo';
    cartItemsNumber = 0;
    mobileQuery: MediaQueryList;
    localeDefault: Locale;
    locales: Locale[] = [];
    localesLoading = true;

    private readonly _mobileQueryListener: () => void;

    constructor(private changeDetectorRef: ChangeDetectorRef,
                private media: MediaMatcher,
                private globalService: GlobalService,
                private globalStorage: GlobalStorage,
                private authService: AuthService) {
        this.mobileQuery = media.matchMedia('(max-width: 600px)');
        this._mobileQueryListener = () => changeDetectorRef.detectChanges();
        this.globalService.cartItemsNumber.subscribe(nb => this.cartItemsNumber = nb);
    }

    async ngOnInit() {
        this.mobileQuery.addEventListener('change', this._mobileQueryListener);
        this.globalService.getAllLocales().subscribe(locales => {
            this.locales = locales;
            this.setDefaultLocale(locales);
            this.localesLoading = false;
        });
        await this.authService.refreshToken();
    }

    ngOnDestroy() {
        this.mobileQuery.removeEventListener('change', this._mobileQueryListener);
    }

    onCountrySelected(country: Country) {
        const locale = country as Locale;
        this.globalService.locale.next(locale.languageCode);
        this.globalStorage.setLocale(locale);
    }

    public logout = () => this.authService.logout();

    public isLogged = () => this.authService.hasAnyRole();

    public adminAllowed = () => this.authService.isAdmin();

    public customerAllowed = () => this.authService.isCustomer();

    public authenticatedAllowed = () => this.adminAllowed() || this.customerAllowed();

    private setDefaultLocale(locales: Locale[]) {
        const fallback = locales.find(e => e.fallback);
        const userLocale = this.globalStorage.getLocale(fallback.alpha2Code);
        const localeExists = locales.some(e => e.alpha2Code === userLocale.alpha2Code);
        if (localeExists) {
            this.localeDefault = userLocale;
        }
    }
}

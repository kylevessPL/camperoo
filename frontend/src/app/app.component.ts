import {MediaMatcher} from '@angular/cdk/layout';
import {ChangeDetectorRef, Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from './config/security/auth.service';
import {GlobalService} from './module/service/global.service';
import {Locale} from './module/models/locale';
import {Country} from '@angular-material-extensions/select-country';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy {
    title = 'Camperoo';
    cartItemsNumber = 0;
    mobileQuery: MediaQueryList;
    countries: Locale[] = [];
    countriesLoading = true;

    private readonly _mobileQueryListener: () => void;

    constructor(private changeDetectorRef: ChangeDetectorRef,
                private media: MediaMatcher,
                private globalService: GlobalService,
                private authService: AuthService) {
        this.mobileQuery = media.matchMedia('(max-width: 600px)');
        this._mobileQueryListener = () => changeDetectorRef.detectChanges();
        this.globalService.cartItemsNumber.subscribe(nb => this.cartItemsNumber = nb);
    }

    async ngOnInit() {
        this.mobileQuery.addEventListener('change', this._mobileQueryListener);
        this.globalService.getAllLocales().subscribe(locales => {
            this.countries = locales;
            this.countriesLoading = false;
        });
        await this.authService.refreshToken();
    }

    ngOnDestroy() {
        this.mobileQuery.removeEventListener('change', this._mobileQueryListener);
    }

    onCountrySelected(locale: Country) {
        console.log(locale as unknown as Locale);
    }

    public logout = () => this.authService.logout();

    public isLogged = () => this.authService.hasAnyRole();

    public adminAllowed = () => this.authService.isAdmin();

    public customerAllowed = () => this.authService.isCustomer();

    public authenticatedAllowed = () => this.adminAllowed() || this.customerAllowed();
}

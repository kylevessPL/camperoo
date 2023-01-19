import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MaterialModule} from './material.module';
import {NgModule} from '@angular/core';
import {environment} from 'src/environments/environment';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatToolbarModule } from '@angular/material/toolbar';

import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {DataTableComponent} from './module/component/data-table/data-table.component';
import {LoginComponent} from './module/page/common/auth/login/login.component';
import {FooterComponent} from './module/component/footer/footer.component';
import {HttpErrorInterceptor} from './config/security/interceptor/http-error.interceptor';
import {HashLocationStrategy, LocationStrategy} from '@angular/common';
import {MessageDialogComponent} from './module/component/message-dialog/message-dialog.component';
import {RECAPTCHA_SETTINGS, RecaptchaFormsModule, RecaptchaModule, RecaptchaSettings} from 'ng-recaptcha';
import {RegisterComponent} from './module/page/common/auth/register/register.component';
import {AccountVerifyComponent} from './module/page/common/auth/account-verify/account-verify.component';
import {HttpRequestInterceptor} from './config/security/interceptor/http-request.interceptor';
import {GlobalService} from './module/service/global.service';
import {TypeSafeMatCellDefDirective} from './module/directive/type-safe-mat-cell-def.directive';
import {DataPropertyGetterPipe} from './module/pipe/data-property-getter.pipe';
import {UsersComponent} from './module/page/admin/administration/users/users.component';
import {PaymentsComponent} from './module/page/common/orders/payments/payments.component';
import {DateFormatterPipe} from './module/pipe/date-formatter.pipe';
import {HttpResponseInterceptor} from './config/security/interceptor/http-response.interceptor';
import {BooleanFormatterPipe} from './module/pipe/boolean-formatter.pipe';
import {BranchesComponent} from './module/page/admin/administration/branches/branches.component';
import {OrdersComponent} from './module/page/common/orders/orders/orders.component';
import {NumberShortFormatterPipe} from './module/pipe/number-short-formatter.pipe';
import {MatSelectCountryModule} from '@angular-material-extensions/select-country';
import {MatChipsModule} from '@angular/material/chips';
import {MatIconModule} from '@angular/material/icon';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatInputModule} from '@angular/material/input';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatSelectModule} from '@angular/material/select';
import {MatBadgeModule} from '@angular/material/badge';

@NgModule({
    declarations: [
        AppComponent,
        DataTableComponent,
        LoginComponent,
        RegisterComponent,
        AccountVerifyComponent,
        PaymentsComponent,
        UsersComponent,
        OrdersComponent,
        FooterComponent,
        BranchesComponent,
        MessageDialogComponent,
        TypeSafeMatCellDefDirective,
        DataPropertyGetterPipe,
        DateFormatterPipe,
        BooleanFormatterPipe,
        NumberShortFormatterPipe
    ],
    imports: [
        MatChipsModule,
        HttpClientModule,
        BrowserAnimationsModule,
        FormsModule,
        ReactiveFormsModule,
        MaterialModule,
        BrowserModule,
        AppRoutingModule,
        MatIconModule,
        MatCardModule,
        MatButtonModule,
        MatProgressSpinnerModule,
        MatToolbarModule,
        MatDialogModule,
        MatFormFieldModule,
        MatInputModule,
        MatDatepickerModule,
        MatSelectModule,
        RecaptchaModule,
        RecaptchaFormsModule,
        MatBadgeModule,
        MatSelectCountryModule.forRoot('en')
    ],
    providers: [
        {
            provide: HTTP_INTERCEPTORS,
            useClass: HttpErrorInterceptor,
            deps: [GlobalService],
            multi: true
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: HttpRequestInterceptor,
            multi: true
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: HttpResponseInterceptor,
            multi: true
        },
        {
            provide: LocationStrategy,
            useClass: HashLocationStrategy
        },
        {
            provide: RECAPTCHA_SETTINGS,
            useValue: {siteKey: environment.recaptchaSiteKey} as RecaptchaSettings,
        }
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}

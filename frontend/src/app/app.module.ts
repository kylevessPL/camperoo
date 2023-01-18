import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MaterialModule} from './material.module';
import {NgModule} from '@angular/core';
import {environment} from 'src/environments/environment';
import {
    MatButtonModule,
    MatCardModule,
    MatChipsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatProgressSpinnerModule,
    MatToolbarModule
} from '@angular/material';

import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {DataTableComponent} from './module/component/data-table/data-table.component';
import {LoginComponent} from './module/page/common/login/login.component';
import {AuthService} from './config/security/auth.service';
import {UserStorage} from './module/storage/user.storage';
import {AdminGuard} from './config/security/guard/admin.guard';
import {
    NGX_MAT_DATE_FORMATS,
    NgxMatDatetimePickerModule,
    NgxMatNativeDateModule,
    NgxMatTimepickerModule
} from '@angular-material-components/datetime-picker';
import {NgxMatMomentModule} from '@angular-material-components/moment-adapter';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatSelectModule} from '@angular/material/select';
import {dateFormats} from './module/models/date-formats';
import {FooterComponent} from './module/component/footer/footer.component';
import {HttpErrorInterceptor} from './config/security/interceptor/http-error.interceptor';
import {HashLocationStrategy, LocationStrategy} from '@angular/common';
import {MessageDialogComponent} from './module/component/message-dialog/message-dialog.component';
import {ClipboardModule} from 'ngx-clipboard';
import {RECAPTCHA_SETTINGS, RecaptchaFormsModule, RecaptchaModule, RecaptchaSettings} from 'ng-recaptcha';
import {RegisterComponent} from './module/page/common/register/register.component';
import {AccountVerifyComponent} from './module/page/common/account-verify/account-verify.component';
import {HttpRequestInterceptor} from './config/security/interceptor/http-request.interceptor';
import {GlobalService} from './module/service/global.service';
import {TypeSafeMatCellDefDirective} from './module/directive/type-safe-mat-cell-def.directive';
import {DataPropertyGetterPipe} from './module/pipe/data-property-getter.pipe';
import {UsersComponent} from './module/page/admin/users/users.component';

@NgModule({
    declarations: [
        AppComponent,
        DataTableComponent,
        LoginComponent,
        RegisterComponent,
        AccountVerifyComponent,
        UsersComponent,
        FooterComponent,
        MessageDialogComponent,
        TypeSafeMatCellDefDirective,
        DataPropertyGetterPipe
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
        NgxMatDatetimePickerModule,
        NgxMatTimepickerModule,
        NgxMatNativeDateModule,
        MatSelectModule,
        NgxMatMomentModule,
        ClipboardModule,
        RecaptchaModule,
        RecaptchaFormsModule
    ],
    providers: [
        UserStorage,
        AuthService,
        AdminGuard,
        {
            provide: NGX_MAT_DATE_FORMATS, useValue: dateFormats
        },
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
            provide: LocationStrategy,
            useClass: HashLocationStrategy
        },
        {
            provide: RECAPTCHA_SETTINGS,
            useValue: {siteKey: environment.recaptchaSiteKey} as RecaptchaSettings,
        }
    ],
    entryComponents: [
        MessageDialogComponent
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}

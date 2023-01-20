import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../../../../config/security/auth.service';
import {NavigationStart, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {LoginData} from '../../../../models/login-data';
import {validation} from '../../../../models/validation';
import {filter, map} from 'rxjs/operators';
import {MatSnackBar} from '@angular/material/snack-bar';
import {GlobalService} from '../../../../service/global.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
    constructor(private authService: AuthService,
                private router: Router,
                private globalService: GlobalService,
                private fb: FormBuilder,
                private snackBar: MatSnackBar) {
    }

    form: FormGroup;

    ngOnInit() {
        if (this.authService.hasAnyRole()) {
            this.router.navigate(['/dashboard']).then(() => {
            });
        }
        this.globalService.httpErrorStatus.subscribe(error => {
            error.status === 401 && this.snackBar.open('Incorrect email or password.', 'Close', {
                duration: 3000
            });
        });
        this.router.events.pipe(
            filter(e => e instanceof NavigationStart),
            map(() => this.router.getCurrentNavigation().extras.state)
        ).subscribe(state => {
            state && state.verified && this.snackBar.open('Account has been verified successfully', 'Close', {
                duration: 3000
            });
        });
        this.form = this.fb.group({
            email: ['', [Validators.required, Validators.pattern(validation.emailPattern)]],
            password: ['', Validators.required],
        });
    }

    login() {
        const data: LoginData = JSON.parse(JSON.stringify(this.form.value));
        this.authService.login(data);
    }
}

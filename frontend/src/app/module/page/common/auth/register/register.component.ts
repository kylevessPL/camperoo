import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../../../../config/security/auth.service';
import {NavigationStart, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {RegisterData} from '../../../../models/register-data';
import {filter, map} from 'rxjs/operators';
import {validation} from '../../../../models/validation';

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
    constructor(private authService: AuthService, private router: Router, private fb: FormBuilder) {
    }

    form: FormGroup;
    success: boolean;

    ngOnInit() {
        if (this.authService.hasAnyRole()) {
            this.router.navigate([`/dashboard`]).then(() => {
            });
        }
        this.router.events.pipe(
            filter(e => e instanceof NavigationStart),
            map(() => this.router.getCurrentNavigation().extras.state)
        ).subscribe(state => {
            this.success = state && state.success;
        });
        this.form = this.fb.group({
            captchaToken: ['', Validators.required],
            email: ['', [Validators.required, Validators.pattern(validation.emailPattern)]],
            password: ['', [Validators.required, Validators.minLength(12), Validators.maxLength(32), Validators.pattern(validation.passwordPattern)]],
            firstName: ['', [Validators.required, Validators.maxLength(255), Validators.pattern(validation.alphaNumericPattern)]],
            lastName: ['', [Validators.required, Validators.maxLength(255), Validators.pattern(validation.alphaNumericPattern)]],
            addressOne: ['', [Validators.required, Validators.maxLength(255), Validators.pattern(validation.alphaNumericPattern)]],
            addressTwo: ['', [Validators.maxLength(255), Validators.pattern(validation.alphaNumericPattern)]],
            zipCode: ['', [Validators.required, Validators.pattern(validation.zipCodePattern)]],
            city: ['', [Validators.required, Validators.maxLength(60), Validators.pattern(validation.alphaNumericPattern)]],
            phoneNumber: ['', [Validators.required, Validators.pattern(validation.phoneNumberPattern)]]
        });
    }

    register() {
        const data: RegisterData = JSON.parse(JSON.stringify(this.form.value), (key, value) => value === '' ? null : value);
        this.authService.register(data);
    }
}

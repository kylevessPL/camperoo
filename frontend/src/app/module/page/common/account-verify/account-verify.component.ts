import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../../../config/security/auth.service';
import {ActivatedRoute, NavigationStart, Router} from '@angular/router';
import {filter, map} from 'rxjs/operators';
import {validation} from '../../../models/validation';

@Component({
    selector: 'app-account-verify',
    templateUrl: './account-verify.component.html',
    styleUrls: ['./account-verify.component.scss']
})
export class AccountVerifyComponent implements OnInit {
    constructor(private authService: AuthService, private router: Router, private route: ActivatedRoute) {
    }

    private readonly _uuidValidator = new RegExp(validation.uuidPattern);
    valid: boolean;

    ngOnInit() {
        if (this.authService.hasAnyRole()) {
            this.router.navigate([`/dashboard`]);
        }
        this.router.events.pipe(
            filter(e => e instanceof NavigationStart),
            map(() => this.router.getCurrentNavigation().extras.state),
            filter(state => state != null)
        ).subscribe(state => {
            this.valid = state.valid;
        });
        this.route.params.subscribe(param => this.verify(param.token));
    }

    private verify(token: string) {
        this.valid = this._uuidValidator.test(token);
        if (this.valid) {
            this.authService.confirm(token);
        }
    }
}

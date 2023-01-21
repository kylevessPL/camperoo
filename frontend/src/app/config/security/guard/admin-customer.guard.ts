import {Injectable} from '@angular/core';
import {CanActivate} from '@angular/router';
import {AuthService} from '../auth.service';
import {AdminGuard} from './admin.guard';
import {CustomerGuard} from './customer.guard';

@Injectable({providedIn: 'root'})
export class AdminCustomerGuard implements CanActivate {

    constructor(private authService: AuthService, private adminGuard: AdminGuard, private customerGuard: CustomerGuard) {
    }

    canActivate = () => this.adminGuard.canActivate() || this.customerGuard.canActivate();
}

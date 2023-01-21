import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegisterComponent} from './module/page/common/auth/register/register.component';
import {AccountVerifyComponent} from './module/page/common/auth/account-verify/account-verify.component';
import {LoginComponent} from './module/page/common/auth/login/login.component';
import {UsersComponent} from './module/page/admin/administration/users/users.component';
import {AdminGuard} from './config/security/guard/admin.guard';
import {PaymentsComponent} from './module/page/common/orders/payments/payments.component';
import {AuthenticatedGuard} from './config/security/guard/authenticated.guard';
import {BranchesComponent} from './module/page/admin/administration/branches/branches.component';
import {OrdersComponent} from './module/page/common/orders/orders/orders.component';
import {CustomerGuard} from './config/security/guard/customer.guard';
import {ProductsComponent} from './module/page/customer/products/products.component';

const routes: Routes = [
    {path: '', component: LoginComponent},
    {path: 'register', component: RegisterComponent},
    {path: 'account-verify/:token', component: AccountVerifyComponent},
    {path: 'dashboard/products', component: ProductsComponent, canActivate: [CustomerGuard]},
    {path: 'dashboard/users', component: UsersComponent, canActivate: [AdminGuard]},
    {path: 'dashboard/orders', component: OrdersComponent, canActivate: [AuthenticatedGuard]},
    {path: 'dashboard/payments', component: PaymentsComponent, canActivate: [AuthenticatedGuard]},
    {path: 'dashboard/branches', component: BranchesComponent, canActivate: [AdminGuard]},
    {path: 'dashboard', redirectTo: '/dashboard/payments'},
    {path: '**', redirectTo: '/'}
];

@NgModule({
    imports: [RouterModule.forRoot(routes, {
        onSameUrlNavigation: 'reload'
    })],
    exports: [RouterModule]
})
export class AppRoutingModule {
}

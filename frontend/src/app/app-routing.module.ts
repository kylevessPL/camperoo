import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegisterComponent} from './module/page/common/register/register.component';
import {AccountVerifyComponent} from './module/page/common/account-verify/account-verify.component';
import {LoginComponent} from './module/page/common/login/login.component';
import {UsersComponent} from './module/page/admin/users/users.component';
import {AdminGuard} from './config/security/guard/admin.guard';

const routes: Routes = [
    {path: '', component: LoginComponent},
    {path: 'register', component: RegisterComponent},
    {path: 'account-verify/:token', component: AccountVerifyComponent},
    {path: 'dashboard/users', component: UsersComponent, canActivate: [AdminGuard]},
    {path: 'dashboard', redirectTo: '/dashboard/users'},
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

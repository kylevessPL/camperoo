import {Injectable} from '@angular/core';

@Injectable()
export class UserStorage {
    private readonly _roles = 'roles';
    private readonly _locale = 'locale';

    getRoles(): string[] {
        const roles = localStorage.getItem(this._roles);
        return JSON.parse(roles);
    };

    setRoles = (roles: string[]) => localStorage.setItem(this._roles, JSON.stringify(roles));

    removeRoles = () => localStorage.removeItem(this._roles);

    isRolesSet = () => !!localStorage.getItem(this._roles);

    setLocale = (locale: string) => localStorage.setItem(this._locale, locale);

    getLocale = () => localStorage.getItem(this._locale);

    removeLocale = () => localStorage.removeItem(this._locale);
}

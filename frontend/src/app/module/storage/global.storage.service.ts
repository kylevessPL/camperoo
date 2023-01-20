import {Injectable} from '@angular/core';
import {UserCart} from '../models/user-cart';
import {Locale} from '../models/locale';

@Injectable({providedIn: 'root'})
export class GlobalStorage {
    private readonly _userId = 'userId';
    private readonly _roles = 'roles';
    private readonly _locale = 'locale';
    private readonly _cart = 'cart';

    setUserId = (userId: number) => localStorage.setItem(this._userId, `${userId}`);

    getUserId = () => +localStorage.getItem(this._userId);

    removeUserId = () => localStorage.removeItem(this._userId);

    getRoles(): string[] {
        const roles = localStorage.getItem(this._roles);
        return JSON.parse(roles);
    };

    setRoles = (roles: string[]) => localStorage.setItem(this._roles, JSON.stringify(roles));

    removeRoles = () => localStorage.removeItem(this._roles);

    isRolesSet = () => !!localStorage.getItem(this._roles);

    setLocale = (locale: Locale) => localStorage.setItem(this._locale, locale.alpha2Code);

    getLocale = () => {
        const locale = localStorage.getItem(this._locale);
        return {
            alpha2Code: locale
        } as Locale;
    };

    getUserCart = () => {
        const userCarts = this.getUserCards();
        return userCarts.find(e => e.userId === this.getUserId()) || {
            userId: this.getUserId(),
            products: new Map<number, number>()
        } as UserCart;
    };

    addToUserCart = (productId: number, quantity: number) => {
        const userCart = this.getUserCart();
        let productQuantity = userCart.products.get(productId) || 0;
        productQuantity += quantity;
        userCart.products.set(productId, productQuantity);
        const userCards = this.getUserCards()
            .filter(e => e.userId !== this.getUserId())
            .concat(userCart);
        localStorage.setItem(this._cart, JSON.stringify(userCards));
    };

    removeUserCart = () => {
        let userCarts = this.getUserCards();
        userCarts = userCarts.filter(e => e.userId !== this.getUserId());
        localStorage.setItem(this._cart, JSON.stringify(userCarts));
    };

    private getUserCards() {
        const cart = localStorage.getItem(this._cart);
        return cart ? JSON.parse(cart) as UserCart[] : [];
    }
}

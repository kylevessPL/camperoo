import {ComponentFixture, TestBed, waitForAsync} from '@angular/core/testing';

import {AccountVerifyComponent} from './account-verify.component';

describe('AccountVerifyComponent', () => {
    let component: AccountVerifyComponent;
    let fixture: ComponentFixture<AccountVerifyComponent>;

    beforeEach(waitForAsync(() => TestBed.configureTestingModule({
        declarations: [AccountVerifyComponent]
    }).compileComponents()));

    beforeEach(() => {
        fixture = TestBed.createComponent(AccountVerifyComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => expect(component).toBeTruthy());
});

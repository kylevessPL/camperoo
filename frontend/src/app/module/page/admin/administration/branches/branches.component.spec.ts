import {ComponentFixture, TestBed, waitForAsync} from '@angular/core/testing';

import {BranchesComponent} from './branches.component';

describe('BranchesComponent', () => {
    let component: BranchesComponent;
    let fixture: ComponentFixture<BranchesComponent>;

    beforeEach(waitForAsync(() => TestBed.configureTestingModule({
        declarations: [BranchesComponent]
    }).compileComponents()));

    beforeEach(() => {
        fixture = TestBed.createComponent(BranchesComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => expect(component).toBeTruthy());
});

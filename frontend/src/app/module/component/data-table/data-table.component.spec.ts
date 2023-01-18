import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {DataTableComponent} from './data-table.component';
import {Page} from '../../models/page';

describe('DataTableComponent', () => {
    let component: DataTableComponent<Page<any>>;
    let fixture: ComponentFixture<DataTableComponent<any>>;

    beforeEach(async(() => TestBed.configureTestingModule({
        declarations: [DataTableComponent]
    }).compileComponents()));

    beforeEach(() => {
        fixture = TestBed.createComponent(DataTableComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => expect(component).toBeTruthy());
});

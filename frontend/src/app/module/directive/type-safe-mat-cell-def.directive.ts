import {CdkCellDef} from '@angular/cdk/table';
import {Directive, Input} from '@angular/core';
import {MatCellDef, MatTableDataSource} from '@angular/material/table';
import {Observable} from 'rxjs';

@Directive({
    selector: '[matCellDef]',
    providers: [{provide: CdkCellDef, useExisting: TypeSafeMatCellDefDirective}]
})
export class TypeSafeMatCellDefDirective<T> extends MatCellDef {
    @Input() matCellDefDataSource: T[] | Observable<T[]> | MatTableDataSource<T>;

    static ngTemplateContextGuard<T>(dir: TypeSafeMatCellDefDirective<T>, ctx: unknown): ctx is { $implicit: T; index: number } {
        return true;
    }
}

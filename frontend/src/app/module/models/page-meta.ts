import {SortDirection} from '@angular/material';

export interface PageMeta {
    page: number,
    size: number,
    sort: string,
    sortDirection: SortDirection;


}

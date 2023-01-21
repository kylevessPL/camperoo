import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpErrorResponse} from '@angular/common/http';
import {TableColumn} from '../../../../models/table-column';
import {GlobalService} from '../../../../service/global.service';
import {CompanyBranch} from '../../../../models/company-branch';
import {BranchService} from '../../../../service/branch.service';

@Component({
    selector: 'app-branches',
    templateUrl: './branches.component.html',
    styleUrls: ['./branches.component.scss']
})
export class BranchesComponent implements OnInit {
    columns: TableColumn[] = [
        {
            name: 'Id',
            key: 'id',
            columnDef: 'id',
            sortable: true
        },
        {
            name: 'Name',
            key: 'name',
            columnDef: 'name',
            sortable: true
        },
        {
            name: 'Address',
            key: 'address',
            columnDef: 'address',
            sortable: true
        },
        {
            name: 'Latitude',
            key: 'latitude',
            columnDef: 'latitude',
            sortable: true
        },
        {
            name: 'Longitude',
            key: 'longitude',
            columnDef: 'longitude',
            sortable: true
        },
        {
            name: 'Email',
            key: 'email',
            columnDef: 'email',
            sortable: true
        },
        {
            name: 'Phone number',
            key: 'phoneNumber',
            columnDef: 'phoneNumber',
            sortable: true
        }
    ];
    data: Observable<CompanyBranch>;
    error: HttpErrorResponse;

    constructor(private branchService: BranchService, private globalService: GlobalService) {
    }

    ngOnInit() {
        this.globalService.httpErrorStatus.subscribe(error => {
            this.error = error;
        });
        this.error = null;
        this.data = this.branchService.getAllCompanyBranches();
    }
}

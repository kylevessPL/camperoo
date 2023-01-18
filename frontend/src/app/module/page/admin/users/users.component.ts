import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpErrorResponse} from '@angular/common/http';
import {User} from '../../../models/user';
import {TableColumn} from '../../../models/table-column';
import {GlobalService} from '../../../service/global.service';
import {UserService} from '../../../service/user.service';
import {PageMeta} from '../../../models/page-meta';

@Component({
    selector: 'app-users',
    templateUrl: './users.component.html',
    styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {
    columns: TableColumn[] = [
        {
            name: 'Id',
            key: 'id',
            sortable: true
        },
        {
            name: 'Email',
            key: 'email',
            sortable: true
        },
        {
            name: 'First name',
            key: 'person.firstName',
            sortable: true
        },
        {
            name: 'Last name',
            key: 'person.lastName',
            sortable: true
        },
        {
            name: 'Role',
            key: 'roles',
            sortable: true
        },
        {
            name: 'Active',
            key: 'active',
            sortable: true
        }
    ];
    data: Observable<User[]>;
    error: HttpErrorResponse;

    constructor(private userService: UserService,
                private globalService: GlobalService) {
    }

    ngOnInit() {
        this.globalService.httpErrorStatus.subscribe(error => {
            this.error = error;
        });
        this.error = null;
    }

    fetchData = (pageMeta: PageMeta) => {
        this.data = this.userService.getAllUsers(pageMeta);
    };
}

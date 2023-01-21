import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpErrorResponse} from '@angular/common/http';
import {TableColumn} from '../../../../models/table-column';
import {GlobalService} from '../../../../service/global.service';
import {PageMeta} from '../../../../models/page-meta';
import {Payment} from '../../../../models/payment';
import {PaymentService} from '../../../../service/payment.service';

@Component({
    selector: 'app-payments',
    templateUrl: './payments.component.html',
    styleUrls: ['./payments.component.scss']
})
export class PaymentsComponent implements OnInit {
    columns: TableColumn[] = [
        {
            name: 'Id',
            key: 'id',
            columnDef: 'id',
            sortable: true
        },
        {
            name: 'Type',
            key: 'type.localizedName',
            columnDef: 'type',
            sortable: true
        },
        {
            name: 'Status',
            key: 'status.localizedName',
            columnDef: 'status',
            sortable: true
        },
        {
            name: 'Status change date',
            key: 'statusChangeDate',
            columnDef: 'statusChangeDate',
            sortable: true
        },
        {
            name: 'Valid',
            key: 'valid',
            columnDef: 'valid',
            sortable: false
        }
    ];
    data: Observable<Payment>;
    pageMeta: PageMeta;
    error: HttpErrorResponse;

    constructor(private paymentService: PaymentService,
                private globalService: GlobalService) {
    }

    ngOnInit() {
        this.globalService.httpErrorStatus.subscribe(error => {
            this.error = error;
        });
        this.error = null;
        this.globalService.locale.subscribe(() => this.fetchData(this.pageMeta));
        this.data = this.paymentService.getAllPayments();
    }

    fetchData = (pageMeta: PageMeta) => {
        this.pageMeta = pageMeta;
        this.data = this.paymentService.getAllPayments(pageMeta);
    };
}

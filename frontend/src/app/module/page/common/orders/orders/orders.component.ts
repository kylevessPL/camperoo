import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpErrorResponse} from '@angular/common/http';
import {TableColumn} from '../../../../models/table-column';
import {GlobalService} from '../../../../service/global.service';
import {PageMeta} from '../../../../models/page-meta';
import {Order} from '../../../../models/order';
import {OrderService} from '../../../../service/order.service';
import {BranchService} from '../../../../service/file.service';
import {TableRowAction} from '../../../../models/table-row-action';

@Component({
    selector: 'app-orders',
    templateUrl: './orders.component.html',
    styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {
    columns: TableColumn[] = [
        {
            name: 'Id',
            key: 'id',
            columnDef: 'id',
            sortable: true
        },
        {
            name: 'Placement date',
            key: 'placementDate',
            columnDef: 'placementDate',
            sortable: true
        },
        {
            name: 'Status change date',
            key: 'statusChangeDate',
            columnDef: 'statusChangeDate',
            sortable: true
        },
        {
            name: 'Status',
            key: 'status.localizedName',
            columnDef: 'status',
            sortable: true
        },
        {
            name: 'Delivery type',
            key: 'deliveryType.localizedName',
            columnDef: 'deliveryType',
            sortable: true
        },
        {
            name: 'Total price',
            key: 'totalPrice',
            columnDef: 'totalPrice',
            sortable: true
        }
    ];
    rowAction: TableRowAction = {
        name: 'Invoice',
        description: 'Download invoice',
        icon: 'download',
        conditionalKey: 'invoiceUuid',
    };
    data: Observable<Order>;
    pageMeta: PageMeta;
    error: HttpErrorResponse;

    constructor(private orderService: OrderService, private branchService: BranchService, private globalService: GlobalService) {
    }

    ngOnInit() {
        this.globalService.httpErrorStatus.subscribe(error => {
            this.error = error;
        });
        this.error = null;
        this.globalService.locale.subscribe(() => this.fetchData(this.pageMeta));
        this.data = this.orderService.getAllOrders();
    }

    fetchData = (pageMeta: PageMeta) => {
        this.pageMeta = pageMeta;
        this.data = this.orderService.getAllOrders(pageMeta);
    };

    downloadInvoice = (order: Order) => {
        this.branchService.getFile(order.invoiceUuid);
    };
}

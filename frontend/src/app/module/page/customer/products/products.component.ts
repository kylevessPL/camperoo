import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpErrorResponse} from '@angular/common/http';
import {Product} from '../../../models/product';
import {PageMeta} from '../../../models/page-meta';
import {ProductService} from '../../../service/product.service';
import {GlobalService} from '../../../service/global.service';

@Component({
    selector: 'app-products',
    templateUrl: './products.component.html',
    styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {
    data: Observable<Product>;
    pageMeta: PageMeta = {
        page: 0,
        size: 5,
        sort: 'id',
        sortDirection: 'asc'
    };
    error: HttpErrorResponse;

    constructor(private productService: ProductService, private globalService: GlobalService) {
    }

    ngOnInit() {
        this.globalService.httpErrorStatus.subscribe(error => {
            this.error = error;
        });
        this.error = null;
        this.globalService.locale.subscribe(() => this.fetchData(this.pageMeta));
        this.fetchData(this.pageMeta);
    }

    fetchData = (pageMeta: PageMeta) => {
        this.pageMeta = pageMeta;
        this.data = this.productService.getAllProducts(pageMeta);
    };

    addToCart = (product: Product) => {

    };
}

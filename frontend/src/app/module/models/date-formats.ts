import {NgxMatDateFormats} from '@angular-material-components/datetime-picker';

export const dateFormats: NgxMatDateFormats = {
    parse: {
        dateInput: 'l, LTS'
    },
    display: {
        dateInput: 'YYYY/MM/DD HH:mm:ss',
        monthYearLabel: 'MMM YYYY',
        dateA11yLabel: 'LL',
        monthYearA11yLabel: 'MMMM YYYY'
    }
};

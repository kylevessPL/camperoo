package pl.piasta.camperoo.address;

import pl.piasta.camperoo.common.exception.BusinessException;
import pl.piasta.camperoo.common.exception.ErrorCode;

class RouteCalculationException extends BusinessException {
    RouteCalculationException() {
        super("Couldn't find route between original and destination points", ErrorCode.ROUTE_CALCULATION_ERROR);
    }
}

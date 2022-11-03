package pl.piasta.camperoo.address.exception;

import pl.piasta.camperoo.common.exception.BusinessException;
import pl.piasta.camperoo.common.exception.ErrorCode;

public class RouteCalculationException extends BusinessException {
    public RouteCalculationException() {
        super("Couldn't find route between original and destination points", ErrorCode.ROUTE_CALCULATION_ERROR);
    }
}

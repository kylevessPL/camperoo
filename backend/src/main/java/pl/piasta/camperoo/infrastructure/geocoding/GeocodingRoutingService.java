package pl.piasta.camperoo.infrastructure.geocoding;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import pl.piasta.camperoo.address.dto.RouteDistanceDto;

import static pl.piasta.camperoo.infrastructure.geocoding.GeocodingParams.WAYPOINTS;

@HttpExchange(GeocodingEndpoints.ROUTING)
interface GeocodingRoutingService {
    @GetExchange
    GeocodingResponse<RouteDistanceDto> findDistanceBetweenCoordinates(@RequestParam(WAYPOINTS) String waypoints);
}

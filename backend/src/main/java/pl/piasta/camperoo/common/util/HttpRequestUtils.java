package pl.piasta.camperoo.common.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

@UtilityClass
public class HttpRequestUtils {
    private static final String X_FORWARED_FOR_HEADER = "X-Forwarded-For";

    public String getClientIp(HttpServletRequest request) {
        String ipAddress = StringUtils.getIfBlank(request.getHeader(X_FORWARED_FOR_HEADER), request::getRemoteAddr);
        return ipAddress.split(",")[0];
    }
}
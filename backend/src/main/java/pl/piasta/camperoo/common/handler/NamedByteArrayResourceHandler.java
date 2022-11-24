package pl.piasta.camperoo.common.handler;

import org.springframework.core.MethodParameter;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import pl.piasta.camperoo.common.util.NamedByteArrayResource;

@ControllerAdvice
class NamedByteArrayResourceHandler implements ResponseBodyAdvice<NamedByteArrayResource> {
    @Override
    public boolean supports(
            MethodParameter returnType,
            @NonNull Class<? extends HttpMessageConverter<?>> converterType
    ) {
        return NamedByteArrayResource.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public NamedByteArrayResource beforeBodyWrite(
            NamedByteArrayResource namedByteArrayResource,
            @NonNull MethodParameter returnType,
            @NonNull MediaType selectedContentType,
            @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
            @NonNull ServerHttpRequest request,
            @NonNull ServerHttpResponse response
    ) {
        setContentDisposition(response, namedByteArrayResource);
        return namedByteArrayResource;
    }

    private void setContentDisposition(ServerHttpResponse response, NamedByteArrayResource namedByteArrayResource) {
        response.getHeaders().add(
                HttpHeaders.CONTENT_DISPOSITION,
                ContentDisposition.attachment()
                        .filename(namedByteArrayResource.getFilename())
                        .build().toString()
        );
        response.getHeaders().setContentLength(namedByteArrayResource.getByteArray().length);
    }
}


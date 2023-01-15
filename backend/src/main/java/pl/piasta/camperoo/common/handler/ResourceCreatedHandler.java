package pl.piasta.camperoo.common.handler;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import pl.piasta.camperoo.common.dto.ResourceCreatedDto;

import java.net.URI;
import java.nio.file.Paths;

@ControllerAdvice
class ResourceCreatedHandler implements ResponseBodyAdvice<Object> {
    private static final String SEGMENT_SELF = "self";

    @Override
    public boolean supports(
            MethodParameter returnType,
            @NonNull Class<? extends HttpMessageConverter<?>> converterType
    ) {
        return ResourceCreatedDto.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            @NonNull MethodParameter returnType,
            @NonNull MediaType selectedContentType,
            @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
            @NonNull ServerHttpRequest request,
            @NonNull ServerHttpResponse response
    ) {
        ResourceCreatedDto responseBody = (ResourceCreatedDto) body;
        URI location = createResourceUri(request, responseBody.getId());
        response.getHeaders().setLocation(location);
        response.getHeaders().setContentLength(0);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private URI createResourceUri(ServerHttpRequest request, Long id) {
        return getCurrentRequest(request)
                .path("/{id}")
                .buildAndExpand(id)
                .normalize()
                .encode()
                .toUri();
    }

    private UriComponentsBuilder getCurrentRequest(ServerHttpRequest request) {
        var currentRequest = ServletUriComponentsBuilder.fromCurrentRequest();
        var isSelf = Paths.get(request.getURI().getPath())
                .getFileName()
                .toString()
                .contains(SEGMENT_SELF);
        return !isSelf ? currentRequest : currentRequest.path("/..");
    }
}

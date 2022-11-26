package pl.piasta.camperoo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.LocaleResolver;

import java.io.IOException;

@RequiredArgsConstructor
class LocaleRequestContextFilter extends OncePerRequestFilter {
    private final LocaleResolver localeResolver;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        var attributes = new ServletRequestAttributes(request, response);
        initContextHolders(request, attributes);
        try {
            filterChain.doFilter(request, response);
        } finally {
            resetContextHolders();
            attributes.requestCompleted();
        }
    }

    protected void initContextHolders(HttpServletRequest request, ServletRequestAttributes requestAttributes) {
        LocaleContextHolder.setLocaleContext(buildLocaleContext(request));
        RequestContextHolder.setRequestAttributes(requestAttributes);
    }

    protected void resetContextHolders() {
        LocaleContextHolder.resetLocaleContext();
        RequestContextHolder.resetRequestAttributes();
    }

    private LocaleContext buildLocaleContext(HttpServletRequest request) {
        request.setAttribute(DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE, localeResolver);
        if (localeResolver instanceof LocaleContextResolver localeContextResolver) {
            return localeContextResolver.resolveLocaleContext(request);
        } else {
            return () -> localeResolver.resolveLocale(request);
        }
    }
}

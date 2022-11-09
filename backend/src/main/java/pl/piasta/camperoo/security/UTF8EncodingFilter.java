package pl.piasta.camperoo.security;

import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;

class UTF8EncodingFilter extends CharacterEncodingFilter {
    UTF8EncodingFilter() {
        setForceEncoding(true);
        setEncoding(StandardCharsets.UTF_8.name());
    }
}

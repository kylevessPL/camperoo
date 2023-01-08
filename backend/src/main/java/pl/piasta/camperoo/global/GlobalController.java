package pl.piasta.camperoo.global;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piasta.camperoo.global.query.LocaleProjection;
import pl.piasta.camperoo.global.query.LocaleQueryClient;

import java.util.List;

@RestController
@RequiredArgsConstructor
class GlobalController {
    private final LocaleQueryClient localeQueryClient;

    @GetMapping("/favicon.ico")
    void disableFavicon() {
    }

    @GetMapping("/global/locales")
    List<LocaleProjection> getAllLocales() {
        return localeQueryClient.findAllLocales();
    }
}

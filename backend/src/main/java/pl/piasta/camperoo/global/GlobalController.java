package pl.piasta.camperoo.global;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class GlobalController {
    @GetMapping("/favicon.ico")
    void disableFavicon() {
    }
}

package pl.piasta.camperoo.file;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piasta.camperoo.common.util.NamedByteArrayResource;
import pl.piasta.camperoo.file.query.FileQueryClient;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
class FileController {
    private final FileQueryClient fileQueryClient;

    @GetMapping(value = "/{uuid}", produces = APPLICATION_OCTET_STREAM_VALUE)
    NamedByteArrayResource getFile(@PathVariable UUID uuid) {
        var file = fileQueryClient.findFileByUuid(uuid);
        return new NamedByteArrayResource(file.getContent(), file.getName());
    }
}

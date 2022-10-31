package pl.piasta.camperoo.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CollectionUtils {

    public static <T> List<T> emptyIfNull(final List<T> list) {
        return Objects.requireNonNullElse(list, Collections.emptyList());
    }
}

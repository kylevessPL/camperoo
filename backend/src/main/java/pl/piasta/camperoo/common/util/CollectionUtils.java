package pl.piasta.camperoo.common.util;

import lombok.experimental.UtilityClass;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@UtilityClass
public class CollectionUtils {
    public <T> List<T> emptyIfNull(final List<T> list) {
        return Objects.requireNonNullElse(list, Collections.emptyList());
    }
}

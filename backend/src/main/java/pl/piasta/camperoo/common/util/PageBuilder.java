package pl.piasta.camperoo.common.util;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.List;

@Getter
@Accessors(fluent = true)
public class PageBuilder<T> {
    @Nullable
    private List<T> content;

    @Nullable
    private Pageable pageable;

    private long total;

    public static <T> PageBuilder<T> fromContent(List<T> content) {
        var builder = new PageBuilder<T>();
        builder.content(content);
        return builder;
    }

    public <P> PageBuilder<T> page(@NonNull Page<P> page) {
        Assert.notNull(page, "Page must not be null");
        this.pageable = page.getPageable();
        this.total = page.getTotalElements();
        return this;
    }

    public PageBuilder<T> content(@NonNull List<T> content) {
        Assert.notNull(content, "Content must not be null");
        this.content = content;
        return this;
    }

    public Page<T> build() {
        return new PageImpl<>(content, pageable, total);
    }
}

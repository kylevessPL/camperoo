package pl.piasta.camperoo.global.query;

public interface LocaleProjection {
    Long getId();

    String getName();

    String getCode();

    boolean isFallback();
}

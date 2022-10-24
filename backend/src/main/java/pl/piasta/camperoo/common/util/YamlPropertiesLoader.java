package pl.piasta.camperoo.common.util;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySourcesPropertyResolver;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.Properties;

import static java.util.Objects.requireNonNull;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class YamlPropertiesLoader {

    public static final YamlPropertiesLoader LAZY = new YamlPropertiesLoader(false);
    public static final YamlPropertiesLoader EAGER = new YamlPropertiesLoader(true);

    private final boolean resolvePlaceholders;

    @NonNull
    public Properties load(String... paths) {
        var resources = Arrays.stream(paths)
                .map(ClassPathResource::new)
                .toArray(Resource[]::new);
        return load(resources);
    }

    @NonNull
    public Properties load(Resource... resources) {
        var properties = createProperties(resources);
        if (resolvePlaceholders) {
            resolvePlaceholders(properties);
        }
        return properties;
    }

    private Properties createProperties(Resource[] resources) {
        var factory = new YamlPropertiesFactoryBean();
        factory.setResources(resources);
        factory.afterPropertiesSet();
        return requireNonNull(factory.getObject());
    }

    private void resolvePlaceholders(Properties properties) {
        var propertySources = new MutablePropertySources();
        new StandardEnvironment().getPropertySources().forEach(propertySources::addLast);
        propertySources.addLast(new PropertiesPropertySource("properties", properties));
        var propertyResolver = new PropertySourcesPropertyResolver(propertySources);
        properties.replaceAll((key, value) -> propertyResolver.getProperty((String) key));
    }
}

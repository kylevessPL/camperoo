package pl.piasta.camperoo.infrastructure.db;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.piasta.camperoo.common.util.PropertiesLoader;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "pl.piasta.camperoo.infrastructure")
@EnableTransactionManagement
@RequiredArgsConstructor
class JpaConfiguration {
    public static final String JPA_CONFIG_PROPERTIES = "jpa-config.yml";
    private final DataSource dataSource;

    @Bean
    @DependsOn("flyway")
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        var entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan("pl.piasta.camperoo.**.domain");
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setJpaProperties(PropertiesLoader.EAGER.load(JPA_CONFIG_PROPERTIES));
        return entityManagerFactoryBean;
    }

    @Bean
    PlatformTransactionManager transactionManager() {
        var transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}

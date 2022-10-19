package pl.piasta.camperoo.config;

import lombok.extern.slf4j.Slf4j;
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
import pl.piasta.camperoo.util.YamlPropertiesLoader;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "pl.piasta.camperoo.repository")
@EnableTransactionManagement
@Slf4j
public class JpaConfig {

    public static final String JPA_CONFIG_PROPERTIES = "jpa-config.yml";

    @Bean
    @DependsOn("flyway")
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        var entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan("pl.piasta.camperoo.model");
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setJpaProperties(YamlPropertiesLoader.EAGER.load(JPA_CONFIG_PROPERTIES));
        return entityManagerFactoryBean;
    }

    @Bean
    PlatformTransactionManager transactionManager(DataSource dataSource) {
        var transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory(dataSource).getObject());
        return transactionManager;
    }

    @Bean
    PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}

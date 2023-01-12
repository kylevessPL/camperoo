package pl.piasta.camperoo.infrastructure.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.piasta.camperoo.file.domain.FilePermissionsManager;
import pl.piasta.camperoo.file.domain.FileProcessor;
import pl.piasta.camperoo.infrastructure.order.OrderJpaRepository;

@Configuration
class FileConfiguration {
    @Bean
    FileDatabaseRepository fileRepository(FileJpaRepository jpaRepository) {
        return new FileDatabaseRepository(jpaRepository);
    }

    @Bean
    FilePermissionsManager filePermissionsManager(OrderJpaRepository jpaRepository) {
        return new FileAccessService(jpaRepository);
    }

    @Bean
    FileProcessor imageProcessor(@Value("${app.file.thumbnail.sizePx}") int thumbnailSize) {
        return new FileProcessingService(thumbnailSize);
    }
}

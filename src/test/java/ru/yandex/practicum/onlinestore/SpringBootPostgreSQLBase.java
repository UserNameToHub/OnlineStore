package ru.yandex.practicum.onlinestore;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
@ActiveProfiles("test")
public abstract class SpringBootPostgreSQLBase {
    protected static PostgreSQLContainer<?> postgres;

    static {
        postgres = new PostgreSQLContainer<>("postgres:15") // Имя и версия образа                .withDatabaseName("testdb") // Название базы данных                .withUsername("junit")      // Логин                .withPassword("junit");     // Пароль        postgres.start();
                .withDatabaseName("testdb")
                .withUsername("admin")
                .withPassword("admin")
                .withInitScript("test.sql")
                .withExposedPorts(5432);

        postgres.start();
    }

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);

   }
}
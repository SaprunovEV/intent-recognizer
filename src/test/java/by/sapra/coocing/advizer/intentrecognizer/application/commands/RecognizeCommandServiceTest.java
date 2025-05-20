package by.sapra.coocing.advizer.intentrecognizer.application.commands;

import by.sapra.coocing.advizer.intentrecognizer.domain.command.RecognizeCommand;
import by.sapra.coocing.advizer.intentrecognizer.domain.events.RecognizeBookedEvent;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@RecordApplicationEvents
@ContextConfiguration(classes = RecognizeCommandServiceConf.class)
class RecognizeCommandServiceTest {
    @Autowired
    ApplicationEvents applicationEvents;

    @Autowired
    RecognizeCommandService sut;

    @Test
    void  whenRecognizeCommand_thenEventPublished() throws Exception {
        RecognizeCommand command = new RecognizeCommand(
                UUID.randomUUID().toString(),
                "test user messate",
                Instant.now()
        );

        sut.recognize(command);

        assertNotNull(applicationEvents.stream(RecognizeBookedEvent.class));
    }


    protected static PostgreSQLContainer<?> postgreSQLContainer;

    static {
        DockerImageName postgres = DockerImageName.parse("postgres:14.5");
        postgreSQLContainer = new PostgreSQLContainer<>(postgres);

        postgreSQLContainer.withReuse(true);
        postgreSQLContainer.withDatabaseName("cooking_adviser");
        postgreSQLContainer.withInitScript("static/schema.sql");
    }

    @DynamicPropertySource
    public static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.jpa.generate-ddl", () -> true);
        registry.add("spring.datasource.driver-class-name",() -> postgreSQLContainer.getDriverClassName());
    }

    @BeforeAll
    static void beforeAll() {
        postgreSQLContainer.start();
    }

    @AfterAll
    static void afterAll() {
        postgreSQLContainer.stop();
    }
}
package by.sapra.coocing.advizer.intentrecognizer.testUtils;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestDbFacadeConfig {
    @Bean
    public TestDbFacade testDbFacade() {
        return new TestDbFacade();
    }
}

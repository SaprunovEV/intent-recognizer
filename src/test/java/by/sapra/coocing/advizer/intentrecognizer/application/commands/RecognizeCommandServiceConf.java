package by.sapra.coocing.advizer.intentrecognizer.application.commands;

import by.sapra.coocing.advizer.intentrecognizer.infrastructure.jpa.RecognizeRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class RecognizeCommandServiceConf {
    @Bean
    public RecognizeCommandService sut(RecognizeRepository repo) {
        return new RecognizeCommandService(repo);
    }

}

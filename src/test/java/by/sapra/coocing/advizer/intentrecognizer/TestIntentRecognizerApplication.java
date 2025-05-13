package by.sapra.coocing.advizer.intentrecognizer;

import org.springframework.boot.SpringApplication;

public class TestIntentRecognizerApplication {

    public static void main(String[] args) {
        SpringApplication.from(IntentRecognizerApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}

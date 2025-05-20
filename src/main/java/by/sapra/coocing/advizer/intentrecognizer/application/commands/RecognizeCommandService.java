package by.sapra.coocing.advizer.intentrecognizer.application.commands;

import by.sapra.coocing.advizer.intentrecognizer.domain.aggregates.Recognize;
import by.sapra.coocing.advizer.intentrecognizer.domain.command.RecognizeCommand;
import by.sapra.coocing.advizer.intentrecognizer.infrastructure.jpa.RecognizeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecognizeCommandService {
    RecognizeRepository repository;
    @Transactional
    public void recognize(RecognizeCommand command) {
        repository.save(new Recognize(command));
    }
}

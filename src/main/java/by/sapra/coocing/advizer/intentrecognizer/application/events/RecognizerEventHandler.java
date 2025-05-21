package by.sapra.coocing.advizer.intentrecognizer.application.events;

import by.sapra.coocing.advizer.intentrecognizer.application.outbound.ChatService;
import by.sapra.coocing.advizer.intentrecognizer.domain.aggregates.Recognize;
import by.sapra.coocing.advizer.intentrecognizer.domain.events.RecognizeBookedEvent;
import by.sapra.coocing.advizer.intentrecognizer.domain.valueObject.IntentType;
import by.sapra.coocing.advizer.intentrecognizer.infrastructure.jpa.RecognizeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecognizerEventHandler {

    private final RecognizeRepository repository;
    private final ChatService chatService;

    @TransactionalEventListener
    public void recognizeCommandHandler(RecognizeBookedEvent event) {

        Recognize recognize = event.recognize();
        IntentType intent = chatService.advice(recognize.getOrder().getUserMessage());

        recognize.updateType(intent);

        repository.save(recognize);
    }


}

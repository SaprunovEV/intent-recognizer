package by.sapra.coocing.advizer.intentrecognizer.application.events;

import by.sapra.coocing.advizer.intentrecognizer.application.outbound.ChatService;
import by.sapra.coocing.advizer.intentrecognizer.domain.aggregates.Recognize;
import by.sapra.coocing.advizer.intentrecognizer.domain.events.RecognizeBookedEvent;
import by.sapra.coocing.advizer.intentrecognizer.domain.events.RecognizeCompleteEvent;
import by.sapra.coocing.advizer.intentrecognizer.domain.valueObject.IntentType;
import by.sapra.coocing.advizer.intentrecognizer.testUtils.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;

import static by.sapra.coocing.advizer.intentrecognizer.domain.aggregates.RecognizeTestDataBuilder.aRecognize;
import static by.sapra.coocing.advizer.intentrecognizer.domain.entityObject.UserOrderTestDataBuilder.aUserOrder;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RecordApplicationEvents
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
class RecognizerEventHandlerTest extends AbstractIntegrationTest {
    @MockitoBean
    ChatService chatService;
    @Autowired
    ApplicationEvents applicationEvents;

    @Autowired
    ApplicationEventPublisher publisher;

    @Test
    void shouldUpdateIntentFieldToDatabase() throws Exception {

        Recognize eventData = getFacade()
                .save(aRecognize()
                        .withUserOrder(aUserOrder())
                        .withIntentType(null));

        Mockito.when(chatService.advice(eventData.getOrder().getUserMessage()))
                .thenReturn(IntentType.SINGLE_RECIPE);

        getFacade().getTransactionTemplate().execute(status -> {
            publisher.publishEvent(new RecognizeBookedEvent(eventData));
            return null;
        });

        assertAll(() -> {
            assertNotNull(eventData.getIntent());
            assertEquals(eventData.getIntent(), IntentType.SINGLE_RECIPE);
        });
    }

    @Test
    void whenUpdateIntent_thenEventPublished() throws Exception {
        Recognize eventData = getFacade()
                .save(aRecognize()
                        .withUserOrder(aUserOrder())
                        .withIntentType(null));

        Mockito.when(chatService.advice(eventData.getOrder().getUserMessage()))
                .thenReturn(IntentType.SINGLE_RECIPE);

        getFacade().getTransactionTemplate().execute(status -> {
            publisher.publishEvent(new RecognizeBookedEvent(eventData));
            return null;
        });

        assertFalse(applicationEvents.stream(RecognizeCompleteEvent.class).findFirst().isEmpty());
    }
}
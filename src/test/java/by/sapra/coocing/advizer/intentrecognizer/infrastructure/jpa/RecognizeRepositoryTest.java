package by.sapra.coocing.advizer.intentrecognizer.infrastructure.jpa;

import by.sapra.coocing.advizer.intentrecognizer.domain.aggregates.Recognize;
import by.sapra.coocing.advizer.intentrecognizer.domain.entityObject.UserOrder;
import by.sapra.coocing.advizer.intentrecognizer.domain.valueObject.IntentType;
import by.sapra.coocing.advizer.intentrecognizer.testUtils.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static by.sapra.coocing.advizer.intentrecognizer.testUtils.RecognizeTestDataBuilder.aRecognize;
import static by.sapra.coocing.advizer.intentrecognizer.testUtils.UserOrderTestDataBuilder.aUserOrder;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
class RecognizeRepositoryTest extends AbstractIntegrationTest {
    @Autowired
    RecognizeRepository sut;

    @Test
    void shouldSaveRecognize() throws Exception {
        Recognize recognize = aRecognize()
                .withUserOrder(getFacade().persistedOnce(aUserOrder()))
                .build();

        Recognize expected = sut.save(recognize);

        Recognize actual = getFacade().find(expected.getId(), Recognize.class);

        assertNotNull(actual);
    }

    @Test
    void shouldNotSaveNewOrder() throws Exception {
        Recognize toSave = new Recognize();
        UserOrder userOrder = new UserOrder();
        userOrder.setId(UUID.randomUUID().toString());
        userOrder.setUserMessage("asdqaweq");
        toSave.setOrder(userOrder);
        toSave.setIntent(IntentType.SINGLE_RECIPE);

        Recognize save = sut.save(toSave);

        assertNull(getFacade().find(save.getOrder().getId(), UserOrder.class));
    }
}
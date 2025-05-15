package by.sapra.coocing.advizer.intentrecognizer.infrastructure.jpa;

import by.sapra.coocing.advizer.intentrecognizer.domain.aggregates.Recognize;
import by.sapra.coocing.advizer.intentrecognizer.testUtils.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static by.sapra.coocing.advizer.intentrecognizer.testUtils.RecognizeTestDataBuilder.aRecognize;
import static by.sapra.coocing.advizer.intentrecognizer.testUtils.UserOrderTestDataBuilder.aUserOrder;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
class RecognizeRepositoryTest extends AbstractIntegrationTest {
    @Autowired
    RecognizeRepository sut;

    @Test
    void shouldSaveRecognize() throws Exception {
        Recognize recognize = aRecognize()
                .withUserOrder(aUserOrder())
                .build();

        Recognize expected = sut.save(recognize);

        Recognize actual = getFacade().find(expected.getId(), Recognize.class);

        assertNotNull(actual);
    }
}
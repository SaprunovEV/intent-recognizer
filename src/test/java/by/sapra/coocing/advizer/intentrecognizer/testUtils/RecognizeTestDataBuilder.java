package by.sapra.coocing.advizer.intentrecognizer.testUtils;

import by.sapra.coocing.advizer.intentrecognizer.domain.aggregates.Recognize;
import by.sapra.coocing.advizer.intentrecognizer.domain.entityObject.UserOrder;
import by.sapra.coocing.advizer.intentrecognizer.domain.valueObject.IntentType;

public class RecognizeTestDataBuilder implements TestDataBuilder<Recognize> {

    private TestDataBuilder<UserOrder> userOrderBuilder = UserOrder::new;
    private IntentType intentType = IntentType.SINGLE_RECIPE;


    private RecognizeTestDataBuilder() {}

    private RecognizeTestDataBuilder(
            TestDataBuilder<UserOrder> userOrderBuilder, IntentType intentType) {
        this.userOrderBuilder = userOrderBuilder;
        this.intentType = intentType;
    }

    public static RecognizeTestDataBuilder aRecognize() {
        return new RecognizeTestDataBuilder();
    }

    public RecognizeTestDataBuilder withUserOrder(TestDataBuilder<UserOrder> userOrder) {
        return new RecognizeTestDataBuilder(userOrder, intentType);
    }

    public RecognizeTestDataBuilder withIntentType(IntentType intentType) {
        return new RecognizeTestDataBuilder(userOrderBuilder, intentType);
    }


    @Override
    public Recognize build() {
        Recognize recognize = new Recognize();

        recognize.setIntent(intentType);
        UserOrder userOrder = userOrderBuilder.build();

        recognize.setOrder(userOrder);

        return recognize;
    }
}

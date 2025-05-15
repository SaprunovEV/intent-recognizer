package by.sapra.coocing.advizer.intentrecognizer.testUtils;

import by.sapra.coocing.advizer.intentrecognizer.domain.entityObject.UserOrder;

import java.time.Instant;
import java.util.UUID;

public class UserOrderTestDataBuilder implements TestDataBuilder<UserOrder> {

    private String userMessage = "test_user_message";
    private Instant sendingTime = Instant.now();

    private UserOrderTestDataBuilder() {}

    private UserOrderTestDataBuilder(String userMessage, Instant sendingTime) {
        this.userMessage = userMessage;
        this.sendingTime = sendingTime;
    }

    public static UserOrderTestDataBuilder aUserOrder() {
        return new UserOrderTestDataBuilder();
    }

    public UserOrderTestDataBuilder withUserMessage(String userMessage) {
        return new UserOrderTestDataBuilder(userMessage, sendingTime);
    }
    public UserOrderTestDataBuilder withSendingTime(Instant sendingTime) {
        return new UserOrderTestDataBuilder(userMessage, sendingTime);
    }


    @Override
    public UserOrder build() {
        UserOrder userOrder = new UserOrder();

        userOrder.setId(UUID.randomUUID().toString());
        userOrder.setUserMessage(userMessage);
        userOrder.setSendingTime(sendingTime);

        return userOrder;
    }
}

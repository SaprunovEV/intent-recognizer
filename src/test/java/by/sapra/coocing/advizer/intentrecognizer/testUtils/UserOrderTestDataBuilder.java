package by.sapra.coocing.advizer.intentrecognizer.testUtils;

import by.sapra.coocing.advizer.intentrecognizer.domain.entityObject.UserOrder;

import java.util.UUID;

public class UserOrderTestDataBuilder implements TestDataBuilder<UserOrder> {

    private String userMessage = "test_user_message";

    private UserOrderTestDataBuilder() {}

    private UserOrderTestDataBuilder(String userMessage) {
        this.userMessage = userMessage;
    }

    public static UserOrderTestDataBuilder aUserOrder() {
        return new UserOrderTestDataBuilder();
    }

    public UserOrderTestDataBuilder withUserMessage(String userMessage) {
        return new UserOrderTestDataBuilder(userMessage);
    }


    @Override
    public UserOrder build() {
        UserOrder userOrder = new UserOrder();

        userOrder.setId(UUID.randomUUID().toString());
        userOrder.setUserMessage(userMessage);

        return userOrder;
    }
}

package by.sapra.coocing.advizer.intentrecognizer.application.outbound;

import by.sapra.coocing.advizer.intentrecognizer.domain.valueObject.IntentType;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    public IntentType advice(String userMessage) {
        return IntentType.SINGLE_RECIPE;
    }
}

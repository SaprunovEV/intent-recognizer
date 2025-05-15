package by.sapra.coocing.advizer.intentrecognizer.domain.events;

public class RecognizeBookedEventData {
    private final Long recognizeId;
    private final String userMessage;

    public RecognizeBookedEventData(Long recognizeId, String userMessage) {
        this.recognizeId = recognizeId;
        this.userMessage = userMessage;
    }
}

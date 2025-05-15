package by.sapra.coocing.advizer.intentrecognizer.domain.events;

public class RecognizeBookedEvent {
    private final RecognizeBookedEventData recognizeBookedEventData;

    public RecognizeBookedEvent(RecognizeBookedEventData recognizeBookedEventData) {
        this.recognizeBookedEventData = recognizeBookedEventData;
    }
}

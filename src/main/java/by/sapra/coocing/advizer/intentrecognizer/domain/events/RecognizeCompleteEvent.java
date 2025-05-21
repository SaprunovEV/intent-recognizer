package by.sapra.coocing.advizer.intentrecognizer.domain.events;

import by.sapra.coocing.advizer.intentrecognizer.domain.aggregates.Recognize;
import lombok.Builder;

@Builder
public record RecognizeCompleteEvent(Recognize data) {
}

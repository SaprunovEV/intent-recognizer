package by.sapra.coocing.advizer.intentrecognizer.domain.command;

import java.time.Instant;

public record RecognizeCommand(String orderId, String message, Instant sendingTime) {}

package by.sapra.coocing.advizer.intentrecognizer.domain.entityObject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Embeddable
@Getter
@Setter
public class UserOrder {
    @Column(name = "user_order_id")
    private String id;

    private String userMessage;

    private Instant sendingTime;
}

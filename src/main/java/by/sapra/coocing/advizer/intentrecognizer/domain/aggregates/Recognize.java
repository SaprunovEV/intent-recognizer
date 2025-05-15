package by.sapra.coocing.advizer.intentrecognizer.domain.aggregates;

import by.sapra.coocing.advizer.intentrecognizer.domain.command.RecognizeCommand;
import by.sapra.coocing.advizer.intentrecognizer.domain.entityObject.UserOrder;
import by.sapra.coocing.advizer.intentrecognizer.domain.valueObject.IntentType;
import by.sapra.coocing.advizer.intentrecognizer.domain.events.RecognizeBookedEvent;
import by.sapra.coocing.advizer.intentrecognizer.domain.events.RecognizeBookedEventData;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.Instant;

@Entity
@Getter
@Setter
public class Recognize extends AbstractAggregateRoot<Recognize> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private UserOrder order;

    @Embedded
    private IntentType intent;

    private Instant createAt;
    private Instant updateAt;

    protected Recognize() {
    }

    public Recognize(RecognizeCommand command) {
        updateOrder(command);

        addDomainEvent(new RecognizeBookedEvent(
                        new RecognizeBookedEventData(this.id, command.message())
                )
        );
    }

    private void updateOrder(RecognizeCommand command) {
        order = new UserOrder();
        order.setSendingTime(command.sendingTime());
        order.setUserMessage(command.message());
        order.setId(command.orderId());
    }

    @PreUpdate
    public void preUpdate() {
        this.updateAt = Instant.now();
    }

    @PrePersist
    public void preCreate() {
        createAt = Instant.now();
        updateAt = Instant.now();
    }

    private void addDomainEvent(Object event) {
        registerEvent(event);
    }
}

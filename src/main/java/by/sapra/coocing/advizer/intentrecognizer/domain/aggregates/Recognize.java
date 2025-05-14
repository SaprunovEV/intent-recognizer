package by.sapra.coocing.advizer.intentrecognizer.domain.aggregates;

import by.sapra.coocing.advizer.intentrecognizer.domain.entityObject.UserOrder;
import by.sapra.coocing.advizer.intentrecognizer.domain.valueObject.IntentType;
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

    @ManyToOne
    @JoinColumn(name = "order_id")
    private UserOrder order;

    @Embedded
    private IntentType intent;

    private Instant create = Instant.now();
    private Instant update = Instant.now();

    @PreUpdate
    public void preUpdate() {
        this.update = Instant.now();
    }
}

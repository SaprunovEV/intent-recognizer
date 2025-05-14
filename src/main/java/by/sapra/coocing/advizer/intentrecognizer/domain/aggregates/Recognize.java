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

    private Instant createAt;
    private Instant updateAt;

    @PreUpdate
    public void preUpdate() {
        this.updateAt = Instant.now();
    }

    @PrePersist
    public void preCreate() {
        createAt = Instant.now();
        updateAt = Instant.now();
    }
}

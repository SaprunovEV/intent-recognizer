package by.sapra.coocing.advizer.intentrecognizer.domain.entityObject;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserOrder {
    @Id
    private String id;

    private String userMessage;
}

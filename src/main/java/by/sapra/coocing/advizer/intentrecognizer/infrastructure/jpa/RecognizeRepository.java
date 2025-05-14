package by.sapra.coocing.advizer.intentrecognizer.infrastructure.jpa;

import by.sapra.coocing.advizer.intentrecognizer.domain.aggregates.Recognize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecognizeRepository extends JpaRepository<Recognize, Long> {
}

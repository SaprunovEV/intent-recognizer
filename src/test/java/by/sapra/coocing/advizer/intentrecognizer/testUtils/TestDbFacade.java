package by.sapra.coocing.advizer.intentrecognizer.testUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.support.TransactionTemplate;

public class TestDbFacade {
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private JdbcTemplate template;
    @Autowired
    private TestEntityManager entityManager;

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    public <T> T find(Object id, Class<T> entityClass) {
        return transactionTemplate.execute(status -> entityManager.find(entityClass, id));
    }

    public <T> T save(TestDataBuilder<T> builder) {
        return transactionTemplate.execute(status -> entityManager.persistAndFlush(builder.build()));
    }

    public void delete(Object entity) {
        transactionTemplate.execute(status -> {
            entityManager.remove(entity);
            return null;
        });
    }

    public <T> TestDataBuilder<T> persistedOnce(TestDataBuilder<T> builder) {
        return new TestDataBuilder<>() {
            private T entity;

            @Override
            public T build() {
                if (entity == null) entity = persisted(builder).build();

                return entity;
            }
        };
    }

    public <T> TestDataBuilder<T> persisted(TestDataBuilder<T> builder) {
        return () -> transactionTemplate.execute(status -> {
            final var entity = builder.build();
            entityManager.persistAndFlush(entity);
            return entity;
        });
    }

    public void cleanDatabase() {
        transactionTemplate.execute(status -> {
            JdbcTestUtils.deleteFromTables(
                    template,
                    "recognize"
            );

            return null;
        });
    }
}

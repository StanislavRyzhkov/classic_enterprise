package company.ryzhkov.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractRepositoryBean {

    @PersistenceContext
    protected EntityManager em;
}

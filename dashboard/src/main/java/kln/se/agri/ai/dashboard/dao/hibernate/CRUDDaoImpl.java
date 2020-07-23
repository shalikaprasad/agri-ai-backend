package kln.se.agri.ai.dashboard.dao.hibernate;

import kln.se.agri.ai.commons.model.AbstractBaseEntity;
import kln.se.agri.ai.persistence.dao.CRUDDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Prasad on 12/15/19.
 */

public class CRUDDaoImpl<T extends AbstractBaseEntity> implements CRUDDao<T> {

    private static final Logger LOG = LoggerFactory.getLogger(CRUDDaoImpl.class);

    /**
     * The entity manager.
     */
    @PersistenceContext
    protected EntityManager entityManager;

    /**
     * The entity class.
     */
    protected final Class<T> entityClass;

    /**
     * Default constructor.
     * <p/>
     * Initializes the entity class type
     */
    @SuppressWarnings("unchecked")
    public CRUDDaoImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public T create(T entity) {
        entityManager.persist(entity);
        entityManager.flush();
        entityManager.refresh(entity);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public T update(T entity) {
        entity = entityManager.merge(entity);
        entityManager.flush();
        entityManager.refresh(entity);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public T detach(T entity) {
        entityManager.detach(entity);
        entity.setId(null);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void delete(Long id) {
        Object reference = entityManager.getReference(entityClass, id);
        entityManager.remove(reference);
        entityManager.flush();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(T entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
        entityManager.flush();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T find(Long id) {
        return entityManager.find(entityClass, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> listAllWithoutDeleted() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(entityClass);
        Root<T> rootEntry = query.from(entityClass);
        CriteriaQuery<T> all = query.select(rootEntry);
        Predicate p1 = (Predicate) builder.and(builder.equal(rootEntry.get("is_deleted"), false));
        query.where((Expression<Boolean>) p1);
        query.orderBy(builder.desc(rootEntry.get("id")));
        TypedQuery<T> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> listAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(entityClass);
        Root<T> rootEntry = query.from(entityClass);
        CriteriaQuery<T> all = query.select(rootEntry);
        query.orderBy(builder.asc(rootEntry.get("id")));
        TypedQuery<T> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long size() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(entityClass)));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

}

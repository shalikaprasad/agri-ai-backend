package kln.se.agri.ai.persistence.dao;

import java.util.List;

/**
 * Created by Prasad on 12/15/19.
 */

public interface CRUDDao<T> {

    /**
     * Persists a new entity.
     */
    T create(T entity);

    /**
     * Updates an existing entity.
     */
    T update(T entity);

    /**
     * Detaches the entity and sets id to null.
     *
     * Note: it doesn't persist/merge the entity.
     */
    T detach(T entity);

    /**
     * Removes the record that is associated with the entity instance.
     */
    void delete(Long id);

    /**
     * Removes the entity from the db.
     */
    void delete(T entity);

    /**
     * Returns an entity based on it's id.
     */
    T find(Long id);

    /**
     * Return a list with ALL entries (without deleted entries).
     */
    List<T> listAll();

    /**
     * Return a list with ALL entries (with deleted entries).
     */
    List<T> listAllWithoutDeleted();

    /**
     * Get size / number of posts in entity table.
     */
    long size();

}

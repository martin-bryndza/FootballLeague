package eu.bato.dao;

/**
 * Created by Martin on 3/9/14.
 */
public interface Dao<T, U> {

    /**
     * Get entity with given id as primary key.
     * @param id primary key of the entity
     * @return
     */
    T get(U id);

    U create(T entity);

    void remove(U id);

    void update(T entity);
}

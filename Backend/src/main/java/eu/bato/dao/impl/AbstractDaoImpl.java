package eu.bato.dao.impl;

import java.util.Map;

import eu.bato.dao.Dao;
import eu.bato.model.entity.Entity;
import eu.bato.model.pk.Pk;

/**
 * Created by Martin on 3/10/14.
 */
public abstract class AbstractDaoImpl<P extends Pk, E extends Entity> implements Dao<E, P> {

    @Override
    public P create(E entity) {
        return createOrUpdate(entity, false);
    }

    @Override
    public void update(E entity) {
        createOrUpdate(entity, true);
    }

    @Override
    public void remove(P pk) {
        E entity = getInstances().remove(pk);
        if (entity == null) {
            throw new IllegalArgumentException("Entity with key " + pk + " does not exist.");
        }
    }

    protected abstract Map<P, E> getInstances();

    protected P createOrUpdate(E entity, boolean update) {
        Map<P, E> instances = getInstances();
        P pk = (P) entity.getPk();
        if (!pk.isValid()) {
            throw new IllegalArgumentException("Part of primary key is null:" + entity);
        }
        if (instances.containsKey(pk) == update) {
            instances.put(pk, entity);
            return pk;
        } else {
            throw new IllegalArgumentException(entity + (update ? " is does not exist." : " already exists."));
        }
    }
}

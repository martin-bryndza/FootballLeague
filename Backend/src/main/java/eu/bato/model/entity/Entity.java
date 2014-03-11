package eu.bato.model.entity;

import eu.bato.model.pk.Pk;

/**
 * Created by Martin on 3/10/14.
 */
public abstract class Entity<P extends Pk> {
    public abstract P getPk();
}

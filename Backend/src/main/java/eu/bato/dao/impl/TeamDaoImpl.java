package eu.bato.dao.impl;

import java.util.HashMap;
import java.util.Map;

import eu.bato.dao.TeamDao;
import eu.bato.model.entity.Team;
import eu.bato.model.pk.TeamPk;
import eu.bato.model.pk.TeamScorePk;

/**
 * Created by Martin on 3/11/14.
 */
public class TeamDaoImpl extends AbstractDaoImpl<TeamPk, Team> implements TeamDao {

    private static final Map<TeamPk, Team> INSTANCES;

    static {
        INSTANCES = new HashMap<>();
    }

    @Override
    public Team get(TeamPk pk) {
        Team team = INSTANCES.get(pk);
        if (team == null) {
            throw new IllegalArgumentException("Entity with key " + pk + " does not exist.");
        }
        return new Team(team);
    }

    @Override
    public void remove(TeamPk pk) {
        Team team = INSTANCES.remove(pk);
        if (team == null) {
            throw new IllegalArgumentException("Entity with key " + pk + " does not exist.");
        }
        new TeamScoreDaoImpl().remove(new TeamScorePk(team));
        new MatchDaoImpl().remove(team);
    }

    @Override
    protected Map<TeamPk, Team> getInstances() {
        return INSTANCES;
    }
}

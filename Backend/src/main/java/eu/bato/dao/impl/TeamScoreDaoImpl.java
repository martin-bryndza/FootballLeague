package eu.bato.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import eu.bato.dao.TeamScoreDao;
import eu.bato.model.entity.TeamScore;
import eu.bato.model.pk.TeamScorePk;

/**
 * Created by Martin on 3/9/14.
 */
public class TeamScoreDaoImpl extends AbstractDaoImpl<TeamScorePk, TeamScore> implements TeamScoreDao {

    private static final Map<TeamScorePk, TeamScore> INSTANCES;

    static {
        INSTANCES = new HashMap<>();
    }

    @Override
    public TeamScore get(TeamScorePk key) {
        return new TeamScore(INSTANCES.get(key));
    }

    @Override
    public List<TeamScore> getAll() {
        List<TeamScore> list = new LinkedList<>();
        list.addAll(INSTANCES.values());
        return Collections.unmodifiableList(list);
    }

    @Override
    protected Map<TeamScorePk, TeamScore> getInstances() {
        return INSTANCES;
    }
}

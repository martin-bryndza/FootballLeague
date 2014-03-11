package eu.bato.dao.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import eu.bato.dao.MatchDao;
import eu.bato.model.entity.Match;
import eu.bato.model.entity.Team;
import eu.bato.model.pk.MatchPk;

/**
 * Created by Martin on 3/9/14.
 */
public class MatchDaoImpl extends AbstractDaoImpl<MatchPk, Match> implements MatchDao {

    private static final Map<MatchPk, Match> INSTANCES;

    static {
        INSTANCES = new HashMap<>();
    }

    @Override
    public Match get(MatchPk pk) {
        Match match = INSTANCES.get(pk);
        if (match == null) {
            throw new IllegalArgumentException("Entity with key " + pk + " does not exist.");
        }
        return new Match(match);
    }

    @Override
    protected Map<MatchPk, Match> getInstances() {
        return INSTANCES;
    }

    @Override
    public List<Match> get(Team team) {
        List<Match> result = new LinkedList<>();
        for (Match match : INSTANCES.values()) {
            if (match.getHomeTeam().equals(team) || match.getGuestTeam().equals(team)) {
                result.add(match);
            }
        }
        return result;
    }

    @Override
    public void remove(Team team) {
        Collection<Match> matches = INSTANCES.values();
        Iterator<Match> iterator = matches.iterator();
        int size = INSTANCES.size();
        for (int i = 0; i < size; i++) {
            Match match = iterator.next();
            if (match.getHomeTeam().equals(team) || match.getGuestTeam().equals(team)) {
                iterator.remove();
            }
        }
    }
}

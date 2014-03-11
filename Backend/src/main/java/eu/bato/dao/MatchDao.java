package eu.bato.dao;

import java.util.List;

import eu.bato.model.entity.Match;
import eu.bato.model.entity.Team;
import eu.bato.model.pk.MatchPk;

/**
 * Created by Martin on 3/9/14.
 */
public interface MatchDao extends Dao<Match, MatchPk> {

    /**
     * Returns all matches of the team.
     * @param team
     * @return all matches where team played
     */
    List<Match> get(Team team);

    /**
     * Removes all matches of the team.
     * @param team
     */
    void remove(Team team);
}

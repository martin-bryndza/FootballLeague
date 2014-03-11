package eu.bato.dao;

import java.util.List;

import eu.bato.model.entity.Team;
import eu.bato.model.entity.TeamScore;
import eu.bato.model.pk.TeamScorePk;

/**
 * Created by Martin on 3/9/14.
 */
public interface TeamScoreDao extends Dao<TeamScore, TeamScorePk> {

    List<TeamScore> getAll();

}

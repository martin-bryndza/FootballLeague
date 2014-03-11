package eu.bato.model.pk;

import java.io.Serializable;

import eu.bato.model.entity.Team;

/**
 * Created by Martin on 3/9/14.
 */
public class TeamScorePk extends Pk implements Serializable {
    private Team team;

    public TeamScorePk(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TeamScorePk that = (TeamScorePk) o;

        if (team != null ? !team.equals(that.team) : that.team != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return 31 * (team != null ? team.hashCode() : 0);
    }

    @Override
    public boolean isValid() {
        return team != null;
    }
}

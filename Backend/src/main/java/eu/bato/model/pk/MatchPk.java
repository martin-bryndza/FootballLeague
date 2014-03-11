package eu.bato.model.pk;

import java.util.Date;

import eu.bato.model.entity.Match;
import eu.bato.model.entity.Team;

/**
 * Created by Martin on 3/9/14.
 */
public class MatchPk extends Pk {

    private Date date;
    private Team homeTeam;

    public MatchPk(Date date, Team homeTeam) {
        this.date = date;
        this.homeTeam = homeTeam;
    }

    public Date getDate() {
        return date;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    @Override
    public boolean isValid() {
        return date!=null && homeTeam!=null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MatchPk matchPk = (MatchPk) o;

        if (date != null ? !date.equals(matchPk.date) : matchPk.date != null) {
            return false;
        }
        if (homeTeam != null ? !homeTeam.equals(matchPk.homeTeam) : matchPk.homeTeam != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (homeTeam != null ? homeTeam.hashCode() : 0);
        return result;
    }
}

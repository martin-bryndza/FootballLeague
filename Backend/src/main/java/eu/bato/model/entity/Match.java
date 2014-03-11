package eu.bato.model.entity;

import java.util.Date;

import eu.bato.model.pk.MatchPk;

/**
 * Created by Martin on 3/8/14.
 */
public class Match extends Entity implements Comparable<Match> {

    private Date date;
    private Team homeTeam;
    private Team guestTeam;
    private int homeScore;
    private int guestScore;

    public Match(Date date, Team homeTeam, Team guestTeam, int homeScore, int guestScore) {
        this.date = date;
        this.homeTeam = homeTeam;
        this.guestTeam = guestTeam;
        this.homeScore = homeScore;
        this.guestScore = guestScore;
    }

    public Match(Match match) {
        this.date = match.getDate();
        this.homeTeam = match.getHomeTeam();
        this.guestTeam = match.getGuestTeam();
        this.homeScore = match.getHomeScore();
        this.guestScore = match.getGuestScore();
    }

    public Date getDate() {
        return date;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getGuestTeam() {
        return guestTeam;
    }

    public void setGuestTeam(Team guestTeam) {
        this.guestTeam = guestTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getGuestScore() {
        return guestScore;
    }

    public void setGuestScore(int guestScore) {
        this.guestScore = guestScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Match match = (Match) o;

        if (date != null ? !date.equals(match.date) : match.date != null) {
            return false;
        }
        if (homeTeam != null ? !homeTeam.equals(match.homeTeam) : match.homeTeam != null) {
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

    @Override
    public MatchPk getPk() {
        return new MatchPk(date, homeTeam);
    }

    @Override
    public String toString() {
        return "Match{" +
                "date=" + date +
                ", homeTeam=" + homeTeam +
                ", guestTeam=" + guestTeam +
                ", homeScore=" + homeScore +
                ", hostScore=" + guestScore +
                '}';
    }

    @Override
    public int compareTo(Match o) {
        int result = date.compareTo(o.getDate());
        return result == 0 ? homeTeam.getName().compareTo(o.getHomeTeam().getName()) : result;
    }
}

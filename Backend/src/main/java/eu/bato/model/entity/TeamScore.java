package eu.bato.model.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import eu.bato.model.pk.TeamScorePk;

/**
 * Created by Martin on 3/9/14.
 */
public class TeamScore extends Entity implements Comparable<TeamScore>, Serializable {
    private Team team;
    private Integer gamesPlayed = 0;
    private Integer goalsGot = 0;
    private Integer goalsGiven = 0;
    private Integer points = 0;
    private Integer currentRank;

    public TeamScore(Team team, Integer currentRank) {
        this.team = team;
        this.currentRank = currentRank;
    }

    public TeamScore(TeamScore teamScore) {
        this.team = teamScore.getTeam();
        this.currentRank = teamScore.getCurrentRank();
    }

    public Team getTeam() {
        return team;
    }

    public Integer getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getGoalsGot() {
        return goalsGot;
    }

    public void setGoalsGot(int goalsGot) {
        this.goalsGot = goalsGot;
    }

    public Integer getGoalsGiven() {
        return goalsGiven;
    }

    public void setGoalsGiven(int goalsGiven) {
        this.goalsGiven = goalsGiven;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getCurrentRank() {
        return currentRank;
    }

    public void setCurrentRank(Integer currentRank) {
        this.currentRank = currentRank;
    }

    public void addGame() {
        gamesPlayed++;
    }

    public void addGoalsGiven(int goals) {
        goalsGiven += goals;
    }

    public void addGoalsGot(int goals) {
        goalsGot += goals;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    @Override
    public String toString() {
        return "TeamScore{" +
                "team=" + team +
                ", gamesPlayed=" + gamesPlayed +
                ", goalsGot=" + goalsGot +
                ", goalsGiven=" + goalsGiven +
                ", points=" + points +
                ", currentRank=" + currentRank +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TeamScore teamScore = (TeamScore) o;

        if (!team.equals(teamScore.team)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return 31 * team.hashCode();
    }

    @Override
    public int compareTo(TeamScore o) {
        int result = o.points.compareTo(this.points);
        result = result == 0 ? ((Integer) (this.goalsGiven - this.goalsGot)).compareTo((Integer) (o.goalsGiven - o.goalsGot)) : result;
        result = result == 0 ? this.currentRank.compareTo(o.currentRank) : result;
        return result == 0 ? 1 : result;
    }

    @Override
    public TeamScorePk getPk() {
        return new TeamScorePk(team);
    }
}

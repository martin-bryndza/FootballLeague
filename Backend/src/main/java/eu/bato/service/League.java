package eu.bato.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import eu.bato.dao.MatchDao;
import eu.bato.dao.TeamDao;
import eu.bato.dao.TeamScoreDao;
import eu.bato.dao.impl.MatchDaoImpl;
import eu.bato.dao.impl.TeamDaoImpl;
import eu.bato.dao.impl.TeamScoreDaoImpl;
import eu.bato.model.entity.Match;
import eu.bato.model.entity.Team;
import eu.bato.model.entity.TeamScore;
import eu.bato.model.pk.TeamScorePk;

/**
 * Created by Martin on 3/8/14.
 */
public class League {

    private static final TeamScoreDao teamScoreDao;
    private static final MatchDao matchDao;
    private static final TeamDao teamDao;

    static {
        teamScoreDao = new TeamScoreDaoImpl();
        matchDao = new MatchDaoImpl();
        teamDao = new TeamDaoImpl();
    }

    //to prevent instantiation and inheritance
    private League() {
    }

    public static void newMatch(Match match) {
        if (match.getHomeTeam().equals(match.getGuestTeam())) {
            throw new IllegalArgumentException("A team can not play match with itself. Current match: " + match);
        }
        matchDao.create(match);
        //update home team score
        TeamScore teamScore = teamScoreDao.get(new TeamScorePk(match.getHomeTeam()));
        updateTeamScore(teamScore, match.getHomeScore(), match.getGuestScore());
        //update guest team score
        teamScore = teamScoreDao.get(new TeamScorePk(match.getGuestTeam()));
        updateTeamScore(teamScore, match.getGuestScore(), match.getHomeScore());
        //update rankings
        updateRankings();
    }

    public static void addTeams(Set<Team> teams) {
        int worstRank = teamScoreDao.getAll().size();
        int i = 0;
        for (Team team : teams) {
            teamDao.create(team);
            teamScoreDao.create(new TeamScore(team, worstRank + ++i));
        }
    }

    public static void removeTeams(Set<Team> teams) {
        for (Team team : teams) {
            teamDao.remove(team.getPk());
        }
    }

    /**
     * @param team
     * @param other
     * @return positive number if team has higher rank than other,
     * negative number if team has lower rank than other,
     * zero otherwise
     */
    public static int compareTeams(Team team, Team other) {
        TeamScore score = teamScoreDao.get(new TeamScorePk(team));
        TeamScore otherScore = teamScoreDao.get(new TeamScorePk(other));
        return score.getCurrentRank() - otherScore.getCurrentRank();
    }

    public static List<Match> getPlayedMatches(Team team) {
        List<Match> matches = matchDao.get(team);
        Collections.sort(matches);
        return matches;
    }

    public static Team getWeakestOpponent(Team team) {
        List<TeamScore> scores = getSortedOpponents(team);
        if (scores.size() > 0) {
            return scores.get(0).getTeam();
        } else {
            return null;
        }
    }

    public static Team getStrongestOpponent(Team team) {
        List<TeamScore> scores = getSortedOpponents(team);
        if (scores.size() > 0) {
            return scores.get(scores.size() - 1).getTeam();
        } else {
            return null;
        }
    }

    private static List<TeamScore> getSortedOpponents(Team team) {
        List<Match> matches = matchDao.get(team);
        //reuse class TeamScore:
        //  points - points gained against the team
        //  goalsGiven - goals scored against the team
        //  goalsGot - goals got against the team
        Map<Team, TeamScore> teams = new HashMap<>();
        for (Match match : matches) {
            Team otherTeam;
            int goalsGiven;
            int goalsGot;
            if (match.getHomeTeam().equals(team)) {
                otherTeam = match.getGuestTeam();
                goalsGiven = match.getHomeScore();
                goalsGot = match.getGuestScore();
            } else {
                otherTeam = match.getHomeTeam();
                goalsGiven = match.getGuestScore();
                goalsGot = match.getHomeScore();
            }
            if (!teams.containsKey(otherTeam)) {
                teams.put(otherTeam, new TeamScore(otherTeam, 0));
            }
            TeamScore score = teams.get(otherTeam);
            score.addPoints(getPoints(goalsGiven, goalsGot));
            score.addGoalsGiven(goalsGiven);
            score.addGoalsGot(goalsGot);
        }
        List<TeamScore> scores = new LinkedList<>(teams.values());
        Collections.sort(scores, new Comparator<TeamScore>() {
            @Override
            public int compare(TeamScore o1, TeamScore o2) {
                int result = o1.getPoints().compareTo(o2.getPoints());
                result = result == 0 ? o1.getGoalsGiven().compareTo(o2.getGoalsGiven()) : result;
                result = result == 0 ? o1.getTeam().getName().compareTo(o2.getTeam().getName()) : result;
                return result;
            }
        });
        return scores;
    }

    public static Map<Team, Team> getPlayOffOpponents() {
        List<TeamScore> scores = getSortedRankings();
        Map<Team, Team> result = new HashMap<>();
        int half = scores.size() / 2;
        int current = 0;
        ListIterator<TeamScore> opponents = scores.listIterator(scores.size() - (scores.size() % 2));
        for (TeamScore score : scores) {
            if (current++ < half) {
                result.put(score.getTeam(), opponents.previous().getTeam());
            }
        }
        return result;
    }

    private static void updateTeamScore(TeamScore teamScore, int goalsGiven, int goalsGot) {
        teamScore.addGame();
        teamScore.addGoalsGiven(goalsGiven);
        teamScore.addGoalsGot(goalsGot);
        teamScore.addPoints(getPoints(goalsGiven, goalsGot));
        teamScoreDao.update(teamScore);
    }

    private static int getPoints(int goalsGiven, int goalsGot) {
        int diff = goalsGiven - goalsGot;
        if (diff > 0) {
            return 2;
        } else if (diff < 0) {
            return 0;
        } else {
            return 1;
        }
    }

    private static void updateRankings() {
        List<TeamScore> scores = new LinkedList<>(teamScoreDao.getAll());
        Collections.sort(scores);
        int i = 1;
        for (TeamScore score : scores) {
            score.setCurrentRank(i++);
            teamScoreDao.update(score);
        }
    }

    public static List<TeamScore> getSortedRankings() {
        List<TeamScore> scores = new LinkedList<>(teamScoreDao.getAll());
        Collections.sort(scores, new Comparator<TeamScore>() {
            @Override
            public int compare(TeamScore o1, TeamScore o2) {
                return o1.getCurrentRank().compareTo(o2.getCurrentRank());
            }
        });
        return scores;
    }
}

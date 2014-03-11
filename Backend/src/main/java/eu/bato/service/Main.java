package eu.bato.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import eu.bato.model.entity.Match;
import eu.bato.model.entity.Team;

/**
 * Created by Martin on 3/9/14.
 */
public class Main {

    public static void main(String[] args) {
        Set<Team> teams = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            teams.add(new Team("team" + i));
        }
        League.addTeams(teams);
        System.out.println(League.getSortedRankings());
        Map<Team, Team> playOff = League.getPlayOffOpponents();
        System.out.println(playOff);

        int i = 0;
        for (Team team : playOff.keySet()) {
            Match match = new Match(new Date(System.currentTimeMillis()), team, playOff.get(team), 1, ++i);
            League.newMatch(match);
            System.out.println(match);
        }

        System.out.println(League.getSortedRankings());

        Iterator<Team> it1 = teams.iterator();
        Iterator<Team> it2 = teams.iterator();
        it2.next();
        i = 0;
        while (it2.hasNext()) {
            Match match = new Match(new Date(System.currentTimeMillis()), it1.next(), it2.next(), 1, ++i);
            League.newMatch(match);
            System.out.println(match);
        }

        System.out.println(League.getSortedRankings());

        for (Team team : teams) {
            System.out.println(team);
            System.out.println(League.getStrongestOpponent(team));
            System.out.println(League.getWeakestOpponent(team));
            System.out.println(League.compareTeams(team, team));
        }

        League.removeTeams(teams);
        System.out.println(League.getSortedRankings());
    }
}

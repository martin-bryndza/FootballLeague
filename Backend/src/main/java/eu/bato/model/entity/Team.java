package eu.bato.model.entity;

import eu.bato.model.pk.Pk;
import eu.bato.model.pk.TeamPk;

/**
 * Created by Martin on 3/8/14.
 */
public class Team extends Entity {

    private String name;

    public Team(String name) {
        this.name = name;
    }

    public Team(Team team){
        this.name = team.getName();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Team team = (Team) o;

        if (!name.equals(team.name)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public TeamPk getPk() {
        return new TeamPk(name);
    }
}

package eu.bato.model.pk;

import eu.bato.model.entity.Team;

/**
 * Created by Martin on 3/10/14.
 */
public class TeamPk extends Pk {

    private String name;

    public TeamPk(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TeamPk teamPk = (TeamPk) o;

        if (name != null ? !name.equals(teamPk.name) : teamPk.name != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}

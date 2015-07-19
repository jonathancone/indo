package rebound.sql.test;

public class Athlete {
    private String firstName;
    private String lastName;

    private Team team;

    public Athlete() {
    }

    public Athlete(String firstName, String lastName) {
	this.firstName = firstName;
	this.lastName = lastName;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public Team getTeam() {
	return team;
    }

    public void setTeam(Team team) {
	this.team = team;
    }

}

package dto;

import model.Runway;

public class RunwayDTO {
    private String spaceport;
    private int number;
    private String status = "FREE";// FREE, BUSY o CLEANING
    private int numLandings = 0;
    private String spaceship;

    public RunwayDTO (String spaceport, int number, String status, int numLandings, String spaceship) {
        this.spaceport = spaceport;
        this.number = number;
        this.status = status;
        this.numLandings = numLandings;
        this.spaceship = spaceship;
    }

    public String getSpaceport() {
        return spaceport;
    }

    public void setSpaceport(String spaceport) {
        this.spaceport = spaceport;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumLandings() {
        return numLandings;
    }

    public void setNumLandings(int numLandings) {
        this.numLandings = numLandings;
    }

    public String getSpaceship() {
        return spaceship;
    }

    public void setSpaceship(String spaceship) {
        this.spaceship = spaceship;
    }

    public static RunwayDTO toDTO(Runway runway) {
        return new RunwayDTO(runway.getSpaceport(),runway.getNumber(),runway.getStatus(),runway.getNumLandings(),runway.getSpaceship());
    }
}

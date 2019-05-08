package model;

import dto.RunwayDTO;

/**
 * Runway Entity class
 */
public class Runway {
    private String spaceport;
    private int number;
    private String status = "FREE";
    private int numLandings = 0;
    private String spaceship;

    public Runway() {
    }

    public Runway(String spaceport, int number, String status, int numLandings, String spaceship) {
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

    public static Runway toModelClass(RunwayDTO dto) {
        return new Runway(dto.getSpaceport(), dto.getNumber(), dto.getStatus(), dto.getNumLandings(), dto.getSpaceship());
    }

    @Override
    public String toString() {
        return "spaceport='" + spaceport + '\'' + ", number=" + number + ", status='" + status + '\'' + ", numLandings=" + numLandings + ", spaceship='" + spaceship;
    }
}

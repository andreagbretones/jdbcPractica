package dto;

import model.Spaceport;

public class SpaceportDTO {

    private String name;
    private String planet;
    private String galaxy;

    public SpaceportDTO(String name, String planet, String galaxy) {
        this.name = name;
        this.planet = planet;
        this.galaxy = galaxy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public String getGalaxy() {
        return galaxy;
    }

    public void setGalaxy(String galaxy) {
        this.galaxy = galaxy;
    }


}

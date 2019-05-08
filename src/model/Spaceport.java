package model;

import dto.SpaceportDTO;

/**
 * SpaceportEntityClass
 */

public class Spaceport {
    private String name;
    private String planet;
    private String galaxy;

    public Spaceport() {
    }

    public Spaceport(String name, String planet, String galaxy) {
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

    /**
     * Transform DTO to Entity
     *
     * @param dto
     * @return
     */
    public static Spaceport toModelClass(SpaceportDTO dto) {
        return new Spaceport(dto.getName(), dto.getPlanet(), dto.getGalaxy());
    }
}

package dto;

import model.Runway;

import java.util.List;

/**
 * DTO to show info of spaceports and its runways
 */
public class AllInformationDTO {

    private String name;
    private String planet;
    private String galaxy;
    private List<Runway> runways;

    public AllInformationDTO() {
    }

    public AllInformationDTO(String name, String planet, String galaxy, List<Runway> runways) {
        this.name = name;
        this.planet = planet;
        this.galaxy = galaxy;
        this.runways = runways;
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

    public List<Runway> getRunways() {
        return runways;
    }

    public void setRunways(List<Runway> runways) {
        this.runways = runways;
    }

    public void showInfo() {
        System.out.println("\nName of spaceport:  " + this.name);
        System.out.println("Galaxy: " + this.galaxy);
        System.out.println("Planet: " + this.planet);
        boolean isFull = true;
        for (Runway runway : this.runways) {
            if (runway.getStatus().equals("FREE")) isFull = false;
        }

        if (!isFull) {
            System.out.println("\tRunways -> ");
            for (Runway runway : this.runways) {
                System.out.println("\t\t" + runway.toString());
            }

        } else {
            System.out.println("\tRunways FULL");
        }

    }
}

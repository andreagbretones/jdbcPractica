package dto;

public class SpaceshipDTO {
    private String name;
    private int capacity;
    private String status;
    private int numflights = 0;

    public SpaceshipDTO(String name, int capacity, String status, int numflights) {
        this.name = name;
        this.capacity = capacity;
        this.status = status;
        this.numflights = numflights;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumflights() {
        return numflights;
    }

    public void setNumflights(int numflights) {
        this.numflights = numflights;
    }

    public static SpaceshipDTO toDTO(SpaceshipDTO spaceship) {
        return new SpaceshipDTO(spaceship.getName(), spaceship.getCapacity(), spaceship.getStatus(), spaceship.getNumflights());
    }
}

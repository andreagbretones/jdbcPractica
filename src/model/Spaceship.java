package model;

import dto.SpaceshipDTO;

/**
 * Spaceship entity class
 */
public class Spaceship {
    //cuando se crea una nave se debe indicar en qué pista de que aeropuerto está.
    // La nave también tiene un status que puede ser: FLYING, LANDED y BROKEN.
    // Cada 15 vuelos, los aviones debén pasar una revisión de mantenimiento por lo que
    // su estado pasará a BROKEN hasta que se revise.
    private String name;
    private int capacity;
    private String status;
    private int numflights = 0;

    public Spaceship(String name, int capacity, String status, int numflights) {
        this.name = name;
        this.capacity = capacity;
        this.status = status;
        this.numflights = numflights;
    }

    public Spaceship() {

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

    public static Spaceship toModelClass(SpaceshipDTO dto) {
        return new Spaceship(dto.getName(), dto.getCapacity(), dto.getStatus(), dto.getNumflights());
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' + ", capacity=" + capacity + ", status='" + status + '\'' + ", numflights=" + numflights;
    }
}

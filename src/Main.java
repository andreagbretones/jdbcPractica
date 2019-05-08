import dto.RunwayDTO;
import dto.SpaceportDTO;
import dto.SpaceshipDTO;
import model.Runway;
import model.Spaceship;
import service.RunwayService;
import service.SpaceportService;
import service.SpaceshipService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        String option;
        BufferedReader br;

        do {
            br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("------- MENU--------");
            System.out.println("a. Crear aeropuerto.");
            System.out.println("b. Crear pista de aterrizaje.");
            System.out.println("c. Crear nave.");
            System.out.println("d. Aterrizar una nave en un planeta.");
            System.out.println("e. Despegar una nave.");
            System.out.println("f. Notificar fin de limpieza de una pista.");
            System.out.println("g. Notificar fin de mantenimiento de una nave.");
            System.out.println("h. Borrar una nave.");
            System.out.println("i. Ver la información de todas las naves de la flota estelar.");
            System.out.println("j. Ver la información de todos los aeropuertos ");
            System.out.println("k. Consultar el estado de todos los aeropuertos.");
            System.out.println("s. Salir.");
            System.out.println("Choose an option: ");

            option = br.readLine();


            switch (option) {

                case "a":
                    try {
                        br = new BufferedReader(new InputStreamReader(System.in));
                        String nameSpaceport;
                        String namePlanet;
                        String nameGalaxy;
                        System.out.println("What's the Spaceport name?");
                        nameSpaceport = br.readLine();
                        System.out.println("In which planet is  the Spaceport " + nameSpaceport + " ?");
                        namePlanet = br.readLine();
                        System.out.println("In which galaxy is  the Spaceport " + nameSpaceport + " ?");
                        nameGalaxy = br.readLine();
                        SpaceportService.insertNewRow(new SpaceportDTO(nameSpaceport, namePlanet, nameGalaxy));
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case "b":
                    try {
                        br = new BufferedReader(new InputStreamReader(System.in));

                        String nameSpaceportRunway;
                        String runwayNumber;
                        String statusRunway;
                        String runwayNumLandings;
                        String runwaySpaceship;
                        System.out.println("What's the Spaceport name?");
                        nameSpaceportRunway = br.readLine();
                        System.out.println("Which number is the Runway from " + nameSpaceportRunway + " ?");
                        runwayNumber = br.readLine();
                        System.out.println("What's the status of the runway number " + runwayNumber + " in Spaceport " + nameSpaceportRunway + " ?");
                        statusRunway = br.readLine();
                        System.out.println("How many landings has the runway number " + runwayNumber + " made so far ?");
                        runwayNumLandings = br.readLine();
                        System.out.println("Which Spaceship has landed at the runway number " + runwayNumber + " ?");
                        runwaySpaceship = br.readLine();

                        RunwayService.insertNewRow(new RunwayDTO(nameSpaceportRunway, Integer.valueOf(runwayNumber), statusRunway, Integer.valueOf(runwayNumLandings), runwaySpaceship));

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    break;

                case "c":
                    try {
                        br = new BufferedReader(new InputStreamReader(System.in));
                        String nameSpaceship;
                        String capacity;
                        String status;
                        String numflights;
                        System.out.println("What's the Spaceship name?");
                        nameSpaceship = br.readLine();
                        System.out.println("Which capacity has the Spaceship " + nameSpaceship + " ?");
                        capacity = br.readLine();
                        System.out.println("Which is the status of  the Spaceship " + nameSpaceship + " ?  FLYING, LANDED or BROKEN ");
                        status = br.readLine();
                        System.out.println("What's the number of flights of the Spaceship " + nameSpaceship + " ?");
                        numflights = br.readLine();

                        SpaceshipService.insertNewRow(new SpaceshipDTO(nameSpaceship, Integer.valueOf(capacity), status, !numflights.isEmpty() ? Integer.valueOf(numflights) : 0));
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case "d":
                    try {
                        br = new BufferedReader(new InputStreamReader(System.in));
                        String nameSpaceshipLand;
                        String planet;
                        String runway;
                        String number;
                        System.out.println("What's the Spaceship you want to land?");
                        nameSpaceshipLand = br.readLine();
                        System.out.println("Showing information...");

                        SpaceportService.getAllInformation().forEach(el -> el.showInfo());

                        System.out.println("In which planet?");
                        planet = br.readLine();
                        System.out.println("In which spaceport? ");
                        runway = br.readLine();
                        System.out.println("Which is the number of the runway");
                        number = br.readLine();

                        SpaceshipService.changeStatusToLanded(nameSpaceshipLand, planet, runway, Integer.valueOf(number));

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case "e":
                    try {
                        br = new BufferedReader(new InputStreamReader(System.in));
                        String nameSpaceshipFly;
                        System.out.println("What's the Spaceship you want to fly?");
                        nameSpaceshipFly = br.readLine();

                        SpaceshipService.changeStatusToFlying(nameSpaceshipFly);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "f":
                    try {
                        br = new BufferedReader(new InputStreamReader(System.in));
                        String spaceport;
                        String spNumber;
                        System.out.println("What's the spaceport name?");
                        spaceport = br.readLine();
                        System.out.println("What's the number value?");
                        spNumber = br.readLine();
                        RunwayService.finishCleaning(spaceport, Integer.valueOf(spNumber));
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "g":
                    try {
                        br = new BufferedReader(new InputStreamReader(System.in));
                        String nameSpaceshipId;

                        System.out.println("What's the Spaceship you want to finish its maintenance?");
                        nameSpaceshipId = br.readLine();
                        SpaceshipService.finishMaintenance(nameSpaceshipId);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "h":
                    try {
                        br = new BufferedReader(new InputStreamReader(System.in));
                        String deleteSpaceship;
                        System.out.println("What's the Spaceship you want to delete?");
                        deleteSpaceship = br.readLine();
                        SpaceshipService.deleteByName(deleteSpaceship);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "i":
                    try {
                        List<Spaceship> resultado = null;
                        resultado = SpaceshipService.findAll();
                        resultado.stream().forEach(element -> System.out.println(element.toString()));
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "j":
                    try {
                        br = new BufferedReader(new InputStreamReader(System.in));
                        String galaxy;
                        System.out.println("Which galaxy do you want to look for ?");
                        galaxy = br.readLine();
                        List<Runway> result = null;

                        result = RunwayService.findByGalaxy(galaxy);

                        if (result.isEmpty()) {
                            System.out.println("No results");
                        }
                        result.forEach(el -> System.out.println(el.toString()));
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "k":
                    try {
                        SpaceportService.getAllInformation().forEach(el -> el.showInfo());
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "s":
                    break;

            }


        } while (!option.equals("s"));

    }


}


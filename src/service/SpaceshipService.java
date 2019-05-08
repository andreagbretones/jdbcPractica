package service;


import dto.RunwayDTO;
import dto.SpaceshipDTO;
import exceptions.RunwayException;
import exceptions.SpaceportException;
import exceptions.SpaceshipException;
import model.Runway;
import model.Spaceport;
import model.Spaceship;
import model.dao.SpaceshipDAO;

import java.sql.SQLException;
import java.util.List;

public class SpaceshipService {
    /**
     * insert new Spaceship
     * @param dto
     * @throws SQLException
     */
    public static void insertNewRow(SpaceshipDTO dto) throws SQLException {
        SpaceshipDAO.insert(Spaceship.toModelClass(dto));
    }

    /**
     * Finish maintenance of a spaceship
     * @param name
     * @throws SQLException
     * @throws SpaceshipException
     */
    public static void finishMaintenance(String name) throws SQLException, SpaceshipException {
        Spaceship row = SpaceshipDAO.findById(name);
        if (row.getStatus().equals("LANDED")) {
            throw new SpaceshipException(1);
        }
        SpaceshipDAO.finishMaintenance(name);
    }

    /**
     * change the status of a Spaceship to landed
     * if numflights >=15 changes to broken automatically for maintenance
     * @param spaceshipName
     * @param planet
     * @param runway
     * @param number
     * @throws SQLException
     * @throws SpaceshipException
     * @throws RunwayException
     * @throws SpaceportException
     */

    public static void changeStatusToLanded(String spaceshipName, String planet, String runway, int number) throws SQLException, SpaceshipException, RunwayException, SpaceportException {

        boolean wrongSpaceportRunway = true;

        List<Spaceport> spaceportsInPlanet = SpaceportService.findByPlanet(planet);

        Runway runway1 = RunwayService.findById(runway, number);

        for (Spaceport el : spaceportsInPlanet) {
            if (el.getName().equals(runway1.getSpaceport())) wrongSpaceportRunway = false;
        }
        if (wrongSpaceportRunway) {
            throw new RunwayException(6);
        }
        if (!runway1.getStatus().equals("FREE")) {
            throw new RunwayException(7);
        }

        Spaceship row = SpaceshipDAO.findById(spaceshipName);
        if (row.getStatus().equals("LANDED") || row.getStatus().equals("BROKEN")) {

            throw new SpaceshipException(6);
        }
        if (row.getNumflights() >= 15) {
            SpaceshipDAO.updateStatus(spaceshipName, "BROKEN");
            System.out.println("Number of flights > 15 , Changing state to BROKEN");
        } else {
            SpaceshipDAO.updateStatus(spaceshipName, "LANDED");
        }

        RunwayService.updateStatusBusy(runway1.getSpaceport(), runway1.getNumber(), spaceshipName);


    }

    /**
     * change the status of a Spaceship to flying
     * if there is numlandings >=5 in runway, then status changes to cleaning
     * @param spaceshipName
     * @throws SQLException
     * @throws SpaceshipException
     * @throws RunwayException
     */
    public static void changeStatusToFlying(String spaceshipName) throws SQLException, SpaceshipException, RunwayException {

        Spaceship row = SpaceshipDAO.findById(spaceshipName);
        if (row.getStatus().equals("FLYING")) {
            throw new SpaceshipException(4);

        }
        Runway runway = RunwayService.findBySpaceship(spaceshipName);
        runway.setStatus(runway.getNumLandings() >= 5 ? "CLEANING" : "FREE");
        runway.setSpaceship(null);

        RunwayService.update(RunwayDTO.toDTO(runway));
        SpaceshipDAO.updateStatus(spaceshipName, "FLYING");

    }

    /**
     * delete Spaceship by name
     * @param name
     * @throws SQLException
     * @throws SpaceshipException
     */
    public static void deleteByName(String name) throws SQLException, SpaceshipException {
        Spaceship row = SpaceshipDAO.findById(name);
        SpaceshipDAO.deleteByName(name);
    }

    /**
     * find all the Spaceships
     * @return
     * @throws SQLException
     * @throws SpaceshipException
     */
    public static List<Spaceship> findAll() throws SQLException, SpaceshipException {
        return SpaceshipDAO.findAll();
    }
}

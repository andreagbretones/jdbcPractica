package service;

import dto.RunwayDTO;
import exceptions.RunwayException;
import model.Runway;
import model.dao.RunwayDAO;

import java.sql.SQLException;
import java.util.List;

public class RunwayService {
    /**
     * @param dto
     * @throws SQLException
     */
    public static void insertNewRow(RunwayDTO dto) throws SQLException {
        RunwayDAO.insert(Runway.toModelClass(dto));
    }

    /**
     * Find Runway by galaxy
     * @param galaxy
     * @return
     * @throws SQLException
     * @throws RunwayException
     */
    public static List<Runway> findByGalaxy(String galaxy) throws SQLException, RunwayException {
        return RunwayDAO.findByGalaxy(galaxy);
    }

    /**
     * * Find Runway by spaceport
     * @param spaceport
     * @return
     * @throws SQLException
     * @throws RunwayException
     */
    public static List<Runway> findBySpaceport(String spaceport) throws SQLException, RunwayException {
        return RunwayDAO.findBySpaceport(spaceport);
    }

    /**
     * * Find Runway by spaceship
     * @param spacehip
     * @return
     * @throws SQLException
     * @throws RunwayException
     */
    public static Runway findBySpaceship(String spacehip) throws SQLException, RunwayException {
        return RunwayDAO.findBySpaceship(spacehip);
    }

    /**
     * the end of cleaning status
     * @param spaceport
     * @param number
     * @throws SQLException
     * @throws RunwayException
     */
    public static void finishCleaning(String spaceport, int number) throws SQLException, RunwayException {
        RunwayDAO.findById(spaceport, number);
        RunwayDAO.finishCleaning(spaceport, number);
    }

    /**
     * update Runway
     * @param runway
     * @throws SQLException
     */
    public static void update(RunwayDTO runway) throws SQLException {
        RunwayDAO.update(Runway.toModelClass(runway));
    }

    /**
     * fins Runway by id
     * @param spaceport
     * @param number
     * @return
     * @throws SQLException
     * @throws RunwayException
     */
    public static Runway findById(String spaceport, int number) throws SQLException, RunwayException {
        return RunwayDAO.findById(spaceport, number);
    }

    /**
     * Update status BUSY
     * @param spaceport
     * @param number
     * @param spaceship
     * @throws SQLException
     */
    public static void updateStatusBusy(String spaceport, int number, String spaceship) throws SQLException {
        RunwayDAO.updateStatus(spaceport, number, "BUSY", spaceship);
    }

}

package service;

import dto.AllInformationDTO;
import dto.SpaceportDTO;
import exceptions.RunwayException;
import exceptions.SpaceportException;
import model.Runway;
import model.Spaceport;
import model.dao.SpaceportDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SpaceportService {
    /**
     * insert new Spaceport
     * @param dto
     * @throws SQLException
     */
    public static void insertNewRow(SpaceportDTO dto) throws SQLException {
        SpaceportDAO.insert(Spaceport.toModelClass(dto));
    }

    /**
     * find Spaceport by planet
     * @param planet
     * @return
     * @throws SQLException
     * @throws SpaceportException
     */
    public static List<Spaceport> findByPlanet(String planet) throws SQLException, SpaceportException {
        return SpaceportDAO.findByPlanet(planet);
    }

    /**
     * get all the information about the Spaceports
     * @return
     * @throws SQLException
     * @throws SpaceportException
     * @throws RunwayException
     */
    public static List<AllInformationDTO> getAllInformation() throws SQLException, SpaceportException, RunwayException {
        List<Spaceport> spaceports = SpaceportDAO.findAll();
        List<AllInformationDTO> result = new ArrayList<>();
        for(Spaceport spaceport: spaceports) {
            List<Runway> runways = RunwayService.findBySpaceport(spaceport.getName());
            result.add(new AllInformationDTO(spaceport.getName(), spaceport.getPlanet(), spaceport.getGalaxy(), runways));
        }
        return result;
    }
}
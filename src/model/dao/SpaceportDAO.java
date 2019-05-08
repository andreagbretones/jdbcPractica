package model.dao;

import exceptions.SpaceportException;
import model.Spaceport;
import util.MysqlClient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Spaceport Dao class
 */
public class SpaceportDAO {

    private static final String TABLE = "spaceport";

    /**
     * Insert a new Spaceport
     *
     * @param spaceport
     * @throws SQLException
     */
    public static void insert(Spaceport spaceport) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        try {
            dbConnection = MysqlClient.getConnection();

            String insertTableSQL = "INSERT INTO " + TABLE + " (name,planet,galaxy) VALUES" + "(?,?,?)";

            preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, spaceport.getName());
            preparedStatement.setString(2, spaceport.getPlanet());
            preparedStatement.setString(3, spaceport.getGalaxy());

            // execute insert SQL stetement
            int result = preparedStatement.executeUpdate();

            if (result != 0) {
                System.out.println("Inserted");
            } else {
                System.out.println("not Inserted");
            }
            System.out.println("Record is inserted into starstucom table!");

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }

        }
    }


    /**
     * Find all rows of spaceport table
     *
     * @return
     * @throws SQLException
     */
    public static List<Spaceport> findAll() throws SQLException, SpaceportException {
        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        try {
            dbConnection = MysqlClient.getConnection();
            preparedStatement = dbConnection.prepareStatement("select * from " + TABLE);

            ResultSet res = preparedStatement.executeQuery();
            return getSpaceports(res);

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }

        }

        return null;
    }


    /**
     * Find by planet
     *
     * @param planet
     * @return
     * @throws SQLException
     */
    public static List<Spaceport> findByPlanet(String planet) throws SQLException, SpaceportException {
        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        try {
            dbConnection = MysqlClient.getConnection();
            preparedStatement = dbConnection.prepareStatement("select * from " + TABLE + " where planet=?");

            preparedStatement.setString(1, planet);
            ResultSet res = preparedStatement.executeQuery();
            return getSpaceports(res);

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }

        }

        return null;
    }


    /**
     * get a single mapping of resultSet
     *
     * @param res
     * @return
     * @throws SQLException
     */
    private static Spaceport getSpaceport(ResultSet res) throws SQLException {
        Spaceport result = new Spaceport();
        result.setName(res.getString("name"));
        result.setPlanet(res.getString("planet"));
        result.setGalaxy(res.getString("galaxy"));
        return result;
    }

    /**
     * get a list mapping of resultSet
     *
     * @param res
     * @return
     * @throws SQLException
     */
    private static List<Spaceport> getSpaceports(ResultSet res) throws SQLException, SpaceportException {
        List<Spaceport> list = new ArrayList<>();
        while (res.next()) {
            list.add(getSpaceport(res));
        }
        if (list.isEmpty()) {
            throw new SpaceportException(2);
        }
        return list;
    }
}

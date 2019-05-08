package model.dao;

import exceptions.RunwayException;
import model.Runway;
import util.MysqlClient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Runway DAO class
 */
public class RunwayDAO {
    private static final String TABLE = "runway";

    /**
     * Inserts a runway
     *
     * @param runway
     * @throws SQLException
     */
    public static void insert(Runway runway) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        try {
            dbConnection = MysqlClient.getConnection();

            String insertTableSQL = "INSERT INTO " + TABLE + " (spaceport,number,status,numlandings,spaceship) VALUES" + "(?,?,?,?,?)";

            preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, runway.getSpaceport());
            preparedStatement.setInt(2, runway.getNumber());
            preparedStatement.setString(3, !runway.getStatus().isEmpty() ? runway.getStatus() : "FREE");
            preparedStatement.setInt(4, runway.getNumLandings());
            if (runway.getSpaceship() != null) {
                preparedStatement.setString(5, runway.getSpaceship());
            } else {
                preparedStatement.setNull(5, Types.VARCHAR);
            }

            int result = preparedStatement.executeUpdate();

            if (result != 0) {
                System.out.println("Inserted");
            } else {
                System.out.println("not Inserted");
            }


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
     * Find runways by galaxy
     *
     * @param galaxy
     * @return
     * @throws SQLException
     */
    public static List<Runway> findByGalaxy(String galaxy) throws SQLException, RunwayException {
        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        try {
            dbConnection = MysqlClient.getConnection();
            preparedStatement = dbConnection.prepareStatement("select * from " + TABLE + " r , spaceport s WHERE s.name = r.spaceport and s.galaxy = ?");
            preparedStatement.setString(1, galaxy);
            ResultSet res = preparedStatement.executeQuery();
            return generateRunways(res);

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
     * Find Runways by spaceport
     *
     * @param spaceport
     * @return
     * @throws SQLException
     */
    public static List<Runway> findBySpaceport(String spaceport) throws SQLException, RunwayException {
        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        try {
            dbConnection = MysqlClient.getConnection();
            preparedStatement = dbConnection.prepareStatement("select * from " + TABLE + " where spaceport=?");

            preparedStatement.setString(1, spaceport);
            ResultSet res = preparedStatement.executeQuery();
            return generateRunways(res);
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
     * find Runways by spaceship
     *
     * @param spaceship
     * @return
     * @throws SQLException
     */
    public static Runway findBySpaceship(String spaceship) throws SQLException, RunwayException {
        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        try {
            dbConnection = MysqlClient.getConnection();
            preparedStatement = dbConnection.prepareStatement("select * from " + TABLE + " where spaceship=?");

            preparedStatement.setString(1, spaceship);
            ResultSet res = preparedStatement.executeQuery();
            if (res.next()) {
                return getRunway(res);
            } else {
                throw new RunwayException(1);
            }
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
     * find By id of the table
     *
     * @param spaceport
     * @param number
     * @return
     * @throws SQLException
     */
    public static Runway findById(String spaceport, int number) throws SQLException, RunwayException {
        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        try {
            dbConnection = MysqlClient.getConnection();
            preparedStatement = dbConnection.prepareStatement("select * from " + TABLE + " where spaceport=? and number=?");

            preparedStatement.setString(1, spaceport);
            preparedStatement.setInt(2, number);
            ResultSet res = preparedStatement.executeQuery();
            if (res.next()) {
                return getRunway(res);
            } else {
                throw new RunwayException(1);
            }
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
     * finish clean of a runway and reset numlandings
     *
     * @param spaceport
     * @param number
     * @throws SQLException
     */
    public static void finishCleaning(String spaceport, int number) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        try {
            dbConnection = MysqlClient.getConnection();

            String insertTableSQL = "update " + TABLE + " set status=?, numlandings = 0 where spaceport=? and number=?";

            preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setInt(3, number);
            preparedStatement.setString(1, "FREE");
            preparedStatement.setString(2, spaceport);

            // execute insert SQL stetement
            int result = preparedStatement.executeUpdate();

            if (result != 0) {
                System.out.println("Cleaning has finished");
            } else {
                System.out.println("not Updated");
            }
            System.out.println("Record is updated into Runway table!");

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
     * Updates a status field of runway
     *
     * @param spaceport
     * @param number
     * @param status
     * @param spaceship
     * @throws SQLException
     */
    public static void updateStatus(String spaceport, int number, String status, String spaceship) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        try {
            dbConnection = MysqlClient.getConnection();

            String insertTableSQL = null;

            if (spaceship != null && status.equals("BUSY")) {
                insertTableSQL = "update " + TABLE + " set status=? , spaceship=?, numlandings = numlandings+1 where spaceport=? and number=?";
                preparedStatement = dbConnection.prepareStatement(insertTableSQL);
                preparedStatement.setInt(4, number);
                preparedStatement.setString(1, status);
                preparedStatement.setString(2, spaceship);
                preparedStatement.setString(3, spaceport);
            } else {
                insertTableSQL = "update " + TABLE + " set status=? where spaceport=? and number=?";
                preparedStatement = dbConnection.prepareStatement(insertTableSQL);
                preparedStatement.setInt(3, number);
                preparedStatement.setString(1, status);
                preparedStatement.setString(2, spaceport);
            }

            int result = preparedStatement.executeUpdate();

            if (result != 0) {
                System.out.println("Status Updated");
            } else {
                System.out.println("not Updated");
            }

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
     * Set null Spaceship Value of runway
     *
     * @param spaceport
     * @param number
     * @throws SQLException
     */
    public static void setNullSpaceship(String spaceport, int number) throws SQLException {

        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        try {
            dbConnection = MysqlClient.getConnection();

            String insertTableSQL = "update " + TABLE + " set spaceship=? where spaceport=? and number=?";

            preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setInt(3, number);
            preparedStatement.setNull(1, Types.VARCHAR);
            preparedStatement.setString(2, spaceport);

            // execute insert SQL stetement
            int result = preparedStatement.executeUpdate();

            if (result != 0) {
                System.out.println("Cleaning has finished");
            } else {
                System.out.println("not Updated");
            }

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
     * updates a runway
     *
     * @param runway
     * @throws SQLException
     */

    public static void update(Runway runway) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        try {
            dbConnection = MysqlClient.getConnection();

            String insertTableSQL = "update " + TABLE + " set status=?,numlandings=?,spaceship=?" + " where spaceport=? and number = ?";

            preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, runway.getStatus());
            preparedStatement.setInt(2, runway.getNumLandings());
            if (runway.getSpaceship() == null) {
                preparedStatement.setNull(3, Types.VARCHAR);
            } else {
                preparedStatement.setString(3, runway.getSpaceship());
            }
            preparedStatement.setString(4, runway.getSpaceport());
            preparedStatement.setInt(5, runway.getNumber());
            // execute insert SQL stetement
            int result = preparedStatement.executeUpdate();

            if (result != 0) {
                System.out.println("Runway Updated");
            } else {
                System.out.println("not Updated");
            }

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
     * generate mapping of resultSet
     *
     * @param res
     * @return
     * @throws SQLException
     */

    private static List<Runway> generateRunways(ResultSet res) throws SQLException {
        List<Runway> list = new ArrayList<>();
        while (res.next()) {
            list.add(getRunway(res));
        }

        return list;
    }

    /**
     * generate a single mapping of resultSet
     *
     * @param res
     * @return
     * @throws SQLException
     */
    private static Runway getRunway(ResultSet res) throws SQLException {
        Runway runway = new Runway();
        runway.setNumber(res.getInt("number"));
        runway.setNumLandings(res.getInt("numLandings"));
        runway.setSpaceport(res.getString("spaceport"));
        runway.setStatus(res.getString("status"));
        runway.setSpaceship(res.getString("spaceship"));
        return runway;
    }
}

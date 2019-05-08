package model.dao;

import exceptions.SpaceshipException;
import model.Spaceship;
import util.MysqlClient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpaceshipDAO {
    private static final String TABLE = "spaceship";

    /**
     * inset a new spaceship
     *
     * @param spaceship
     * @throws SQLException
     */
    public static void insert(Spaceship spaceship) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        try {
            dbConnection = MysqlClient.getConnection();

            String insertTableSQL = "INSERT INTO " + TABLE + " (name,capacity,status,numflights) VALUES" + "(?,?,?,?)";

            preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, spaceship.getName());
            preparedStatement.setInt(2, spaceship.getCapacity());
            preparedStatement.setString(3, spaceship.getStatus());
            preparedStatement.setInt(4, spaceship.getNumflights());


            // execute insert SQL stetement
            int result = preparedStatement.executeUpdate();

            if (result != 0) {
                System.out.println("Inserted");
            } else {
                System.out.println("not Inserted");
            }
            System.out.println("Record is inserted into Spaceship table!");

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
     * Updates status field
     * If is FLYING increments the number of flights
     *
     * @param name
     * @param status
     * @throws SQLException
     * @throws SpaceshipException
     */
    public static void updateStatus(String name, String status) throws SQLException, SpaceshipException {
        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        try {
            dbConnection = MysqlClient.getConnection();
            String insertTableSQL = null;
            if (status.equals("FLYING")) {
                insertTableSQL = "update " + TABLE + " set status=?, numflights=numflights+1 where name=? and status <> 'BROKEN'";
            } else {
                insertTableSQL = "update " + TABLE + " set status=? where name=?";
            }
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setString(2, name);
            preparedStatement.setString(1, status);
            // execute insert SQL stetement
            int result = preparedStatement.executeUpdate();

            if (result != 0) {
                System.out.println("Updated");
            } else {
                throw new SpaceshipException(5);
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
     * Delete by name
     *
     * @param name
     * @return
     * @throws SQLException
     * @throws SpaceshipException
     */
    public static Spaceship deleteByName(String name) throws SQLException, SpaceshipException {
        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        try {
            dbConnection = MysqlClient.getConnection();
            preparedStatement = dbConnection.prepareStatement("delete from " + TABLE + " where name=?");

            preparedStatement.setString(1, name);

            int result = preparedStatement.executeUpdate();

            if (result != 0) {
                System.out.println("Deleted");
            } else {
                System.out.println("not Deleted");
                throw new SpaceshipException(1);
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
     * finish maintenance
     * Changes status to landed and reset numflights
     *
     * @param name
     * @throws SQLException
     */
    public static void finishMaintenance(String name) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        try {
            dbConnection = MysqlClient.getConnection();
            String insertTableSQL = null;
            insertTableSQL = "update " + TABLE + " set status= 'LANDED', numflights = '0' where name=?";
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, name);
            // execute insert SQL stetement
            int result = preparedStatement.executeUpdate();

            if (result != 0) {
                System.out.println("Maintenance finished");
            } else {
                System.out.println("Maintenance finishing error");
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
     * Find by id
     *
     * @param name
     * @return
     * @throws SQLException
     * @throws SpaceshipException
     */
    public static Spaceship findById(String name) throws SQLException, SpaceshipException {
        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        try {
            dbConnection = MysqlClient.getConnection();
            preparedStatement = dbConnection.prepareStatement("select * from " + TABLE + " where name=?");

            preparedStatement.setString(1, name);
            ResultSet res = preparedStatement.executeQuery();

            if (res.next()) {
                return getSpaceship(res);
            } else {
                throw new SpaceshipException(1);
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
     * Find all rows of the spaceship table
     *
     * @return
     * @throws SQLException
     */
    public static List<Spaceship> findAll() throws SQLException, SpaceshipException {
        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        try {
            dbConnection = MysqlClient.getConnection();
            preparedStatement = dbConnection.prepareStatement("select * from " + TABLE);

            ResultSet res = preparedStatement.executeQuery();
            List<Spaceship> list = getSpaceships(res);
            return list;

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
     * get a list mapping of a resultSet
     *
     * @param res
     * @return
     * @throws SQLException
     */
    private static List<Spaceship> getSpaceships(ResultSet res) throws SQLException, SpaceshipException {
        List<Spaceship> list = new ArrayList<>();
        while (res.next()) {
            list.add(getSpaceship(res));
        }
        if (list.isEmpty()) {
            throw new SpaceshipException(1);
        }
        return list;
    }


    /**
     * get a single mapping of a resultSet
     *
     * @param res
     * @return
     * @throws SQLException
     */
    private static Spaceship getSpaceship(ResultSet res) throws SQLException {
        Spaceship result = new Spaceship();
        result.setName(res.getString("name"));
        result.setNumflights(res.getInt("numflights"));
        result.setStatus(res.getString("status"));
        result.setCapacity(res.getInt("capacity"));
        return result;
    }
}


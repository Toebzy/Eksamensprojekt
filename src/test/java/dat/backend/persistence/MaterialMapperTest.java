package dat.backend.persistence;


import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.MaterialFacade;
import dat.backend.model.persistence.UserFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class MaterialMapperTest
{
    // TODO: Change mysql login credentials if needed below

    private final static String USER = "dev";
    private final static String PASSWORD = "3r!DE32*/fDe";
    private final static String URL = "jdbc:mysql://64.226.113.12:3306/carport_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";

    private static ConnectionPool connectionPool;

    @BeforeAll
    public static void setUpClass()
    {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);

        try (Connection testConnection = connectionPool.getConnection())
        {
            try (Statement stmt = testConnection.createStatement())
            {
                // Create test database - if not exist
                stmt.execute("CREATE DATABASE  IF NOT EXISTS carport_test;");

                // TODO: Create user table. Add your own tables here
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.user LIKE carport.user;");
            }
        }
        catch (SQLException throwables)
        {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @BeforeEach
    void setUp()
    {
        try (Connection testConnection = connectionPool.getConnection())
        {
            try (Statement stmt = testConnection.createStatement())
            {
                // TODO: Remove all rows from all tables - add your own tables here
                stmt.execute("delete from user");

                // TODO: Insert a few users - insert rows into your own tables here
                stmt.execute("insert into user (iduser, email, password,  zipcode, address, name, phonenumber) " +
                        "values ('1','user','1234','4200','Lyngby','Morten','112')");
            }
        }
        catch (SQLException throwables)
        {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @Test
    void testConnection() throws SQLException
    {
        Connection connection = connectionPool.getConnection();
        assertNotNull(connection);
        if (connection != null)
        {
            connection.close();
        }
    }

    @Test
    void getpriceTest() throws DatabaseException
    {
        try(Connection testConnection = connectionPool.getConnection())
        {
            assertEquals(32, MaterialFacade.getPrice(2, testConnection));
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }
    @Test
    void getLengthTest() throws DatabaseException
    {
        try(Connection testConnection = connectionPool.getConnection())
        {
            assertEquals(300, MaterialFacade.getLength(1, testConnection));
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }
    @Test
    void getUnitTest() throws DatabaseException, SQLException
    {
        try(Connection testConnection = connectionPool.getConnection())
        {
            assertEquals("meter", MaterialFacade.getUnit(3, testConnection));
        }
    }
    @Test
    void getDescriptionTest() throws DatabaseException
    {
        try(Connection testConnection = connectionPool.getConnection())
        {
            assertEquals("hej", MaterialFacade.getDescription(3, testConnection));
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }
}
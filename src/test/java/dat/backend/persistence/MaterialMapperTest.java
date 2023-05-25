package dat.backend.persistence;


import dat.backend.model.entities.Material;
import dat.backend.model.entities.Partslist;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.MaterialFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;

import static org.junit.jupiter.api.Assertions.*;

class MaterialMapperTest
{
    // TODO: Change mysql login credentials if needed below

    private final static String USER = "dev";
    private final static String PASSWORD = "3r!DE32*/fDe";
    private final static String URL = "jdbc:mysql://64.226.113.12:3306/carport_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";

    private static ConnectionPool connectionPool;



    @BeforeAll
    public static void setUpClass() throws SQLException {
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
    void getMaterialByIdTest() throws DatabaseException {
        List<Material> materialList = MaterialFacade.getMaterialById(1,connectionPool);
        List<Material> expectedList = new ArrayList<>();
        expectedList.add(new Material(1,360,34.95,"meter","25x200 mm. trykimp.Brædt"));
        expectedList.add(new Material(2,540,34.95,"meter","25x200 mm. trykimp.Brædt"));
        assertEquals(expectedList.toString(),materialList.toString());
    }

    @Test
    void getpriceTest() throws DatabaseException
    {
        assertEquals(34.95, MaterialFacade.getPrice(2, connectionPool));
        assertNull(MaterialFacade.getPrice(20, connectionPool));
    }
    @Test
    void getLengthTest() throws DatabaseException
    {
        assertEquals(360, MaterialFacade.getLength(1, connectionPool));
        assertEquals(0,MaterialFacade.getLength(20, connectionPool));
    }
    @Test
    void getUnitTest() throws DatabaseException
    {
        assertEquals("meter", MaterialFacade.getUnit(4, connectionPool));
        assertNull(MaterialFacade.getUnit(20, connectionPool));

    }
    @Test
    void getDescriptionTest() throws DatabaseException
    {
        assertEquals("25x125mm. trykimp.Brædt", MaterialFacade.getDescription(4, connectionPool));
        assertNull(MaterialFacade.getDescription(20, connectionPool));
    }
}

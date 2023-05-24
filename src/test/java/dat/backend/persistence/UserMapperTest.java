package dat.backend.persistence;


import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest
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
                stmt.execute("CREATE DATABASE IF NOT EXISTS carport_test;");

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
                stmt.execute("insert into carport_test.user (iduser, email, password,  zipcode, address, name, phonenumber) " +
                        "values ('1','testUser','1234','4200','Lyngby','Morten','112')");
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
    void testCreateUser() throws DatabaseException
    {
        UserFacade.createUser("userCreationTest", "1234", "4200","Lyngby","Morten","112",connectionPool);
        assertDoesNotThrow(()->UserFacade.login("userCreationTest","1234",connectionPool));
    }
    @Test
    void testLogin() throws DatabaseException
    {
        User actualUser = UserFacade.login("testUser", "1234", connectionPool);
        User expectedUser = new User("10", "testUser", "1234", "100","4200","Lyngby","Morten","112","0");
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void invalidPasswordLogin()
    {
        assertThrows(DatabaseException.class, () -> UserFacade.login("user", "123", connectionPool));
    }

    @Test
    void invalidEmailLogin()
    {
        assertThrows(DatabaseException.class, () -> UserFacade.login("bob", "1234", connectionPool));
    }

    @Test
    void testExistingEmailLogin()
    {
        assertThrows(DatabaseException.class, () -> UserFacade.createUser( "testUser", "1234", "4200","Lyngby","Morten","112",connectionPool));
    }

    @Test
    void testCheckEmail() throws DatabaseException
    {
        assertTrue(UserFacade.checkEmail("testUser", connectionPool));
        assertFalse(UserFacade.checkEmail("notauser", connectionPool));
    }

    @Test
    void testCheckZip() throws DatabaseException
    {
        assertTrue(UserFacade.checkZip("4200", connectionPool));
        assertTrue(UserFacade.checkZip("2700", connectionPool));
        assertFalse(UserFacade.checkZip("3100", connectionPool));
    }

    @Test
    void testBalanceChange() throws DatabaseException {
        assertDoesNotThrow(()-> UserFacade.balanceChange("500","1",connectionPool));
        String sql = "SELECT balance FROM carport_test.user where iduser=1;";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    assertEquals("500",rs.getString("balance"));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Error fetching balance. Something went wrong with the database");
        }
    }


}
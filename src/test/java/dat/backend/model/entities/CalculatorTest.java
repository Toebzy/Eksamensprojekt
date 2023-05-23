package dat.backend.model.entities;

import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private final static String USER = "dev";
    private final static String PASSWORD = "3r!DE32*/fDe";
    private final static String URL = "jdbc:mysql://64.226.113.12:3306/carport_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";
    private static ConnectionPool connectionPool;
    private Calculator calc;


    @BeforeAll
    public static void setUpClass() {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);

        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement stmt = testConnection.createStatement()) {
                // Create test database - if not exist
                stmt.execute("CREATE DATABASE  IF NOT EXISTS carport_test;");

                // TODO: Create user table. Add your own tables here
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.user LIKE carport.user;");

            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @BeforeEach
    void setUp() {
        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement stmt = testConnection.createStatement()) {
                // TODO: Remove all rows from all tables - add your own tables here
                stmt.execute("delete from carport_test.user");
                stmt.execute("delete from carport_test.order");

                // TODO: Insert a few users - insert rows into your own tables here
                stmt.execute("insert into carport_test.user (iduser, email, password,  zipcode, address, name, phonenumber) " +
                        "values ('1','testUser','1234','4200','Lyngby','Morten','112')");
                stmt.execute("insert into carport_test.order (idorder, status, carportwidth, carportlength, carportheight, price, paymentstatus, iduser) " +
                        "values ('1','processing','1200','600','210','6000','0','1')");
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @Test
    void testConnection() throws SQLException {
        Connection connection = connectionPool.getConnection();
        assertNotNull(connection);
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    void calculateRafterAmount() {
        assertEquals(10,Calculator.calculateRafterAmount(600,600));
        assertEquals(2,Calculator.calculateRafterAmount(100,100));
        assertEquals(19,Calculator.calculateRafterAmount(1200,588));
        assertEquals(0,Calculator.calculateRafterAmount(0,0));
    }

    @Test
    void calculatePoleAmount() {
        assertEquals(8,Calculator.calculatePoleAmount(600,600));
        assertEquals(4,Calculator.calculatePoleAmount(100,100));
        assertEquals(12,Calculator.calculatePoleAmount(1200,588));
        assertEquals(4,Calculator.calculatePoleAmount(0,0));
                            //min is set at 4
    }

    @Test
    void calculateBeamAmount() {
        assertEquals(2,Calculator.calculateBeamAmount(600));
        assertEquals(2,Calculator.calculateBeamAmount(100));
        assertEquals(4,Calculator.calculateBeamAmount(1200));
        assertEquals(0,Calculator.calculateBeamAmount(0));
    }

    @Test
    void calculateAmount() throws DatabaseException {
        assertEquals(2,Calculator.calculateAmount(600,600,connectionPool));
        assertEquals(2,Calculator.calculateAmount(100,100,connectionPool));
        assertEquals(4,Calculator.calculateAmount(1200,588,connectionPool));
        assertEquals(0,Calculator.calculateAmount(0,0,connectionPool));
    }

    @Test
    void calculateFasciaAmount() {
    }

    @Test
    void fasciaMaterial() {
    }

    @Test
    void roofMaterial() {
    }

    @Test
    void calculateRoofAmount() {
    }

    @Test
    void getTotalPrice() {
    }

    @Test
    void getPartsList() {
    }

    @Test
    void getWidth() {
    }

    @Test
    void getLength() {
    }

    @Test
    void getHeight() {
    }
}
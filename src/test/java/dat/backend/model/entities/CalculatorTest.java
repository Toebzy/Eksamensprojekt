package dat.backend.model.entities;

import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private final static String USER = "dev";
    private final static String PASSWORD = "3r!DE32*/fDe";
    private final static String URL = "jdbc:mysql://64.226.113.12:3306/carport";
    private static ConnectionPool connectionPool;
    Calculator calc;
    Calculator calc_max;
    Calculator calc_min;

    @BeforeAll
    public static void setUpClass() {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);

        try (Connection testConnection = connectionPool.getConnection()) {
            testConnection.createStatement();
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }
    @BeforeEach
    void setUp() throws SQLException, DatabaseException {
        this.calc = new Calculator(200,600,200,connectionPool);

        //MAX possible length, width, height
        this.calc_max = new Calculator(2700,2700,210,connectionPool);

        //MIN possible length, width, height
        this.calc_min = new Calculator(100,100,100,connectionPool);
    }

    @Test
    void calculateRafterAmount() throws SQLException, DatabaseException {

        assertEquals(4,calc.getAmountOfRafters());

        //MAX possible length, width, height
        assertEquals(210,calc_max.getAmountOfRafters());

        //MIN possible length, width, height
        assertEquals(2,calc_min.getAmountOfRafters());
    }
    @Test
    void calculatePoleAmount() throws SQLException, DatabaseException {
        assertEquals(6,calc.getAmountOfPoles());

        //MAX possible length, width, height
        assertEquals(36,calc_max.getAmountOfPoles());

        //MIN possible length, width, height
        assertEquals(4,calc_min.getAmountOfPoles());
    }

    @Test
    void calculateBeamAmount() throws SQLException, DatabaseException {
        assertEquals(2,calc.getAmountOfBeams());

        //MAX possible length, width, height
        assertEquals(10,calc_max.getAmountOfBeams());

        //MIN possible length, width, height
        assertEquals(2,calc_min.getAmountOfBeams());
    }
    @Test
    void calculateFasciaAmount() throws SQLException, DatabaseException {
        int[] result1=calc.getFasciaAmount();
        int[] test1={6,0};
        assertArrayEquals(test1,result1);

        //MAX possible length, width, height
        int[] result2=calc_max.getFasciaAmount();
        int[] test2={12,12};
        assertArrayEquals(test2,result2);

        //MIN possible length, width, height
        int[] result3=calc_min.getFasciaAmount();
        int[] test3={4,0};
        assertArrayEquals(test3,result3);
    }
    @Test
    void calculateRoofAmount() throws SQLException, DatabaseException {

        int[] result1=calc.getRoofAmount();
        int[] test1={6,0};
        assertArrayEquals(test1,result1);

        //MAX possible length, width, height
        int[] result2=calc_max.getRoofAmount();
        int[] test2={81,81};
        assertArrayEquals(test2,result2);

        //MIN possible length, width, height
        int[] result3=calc_min.getRoofAmount();
        int[] test3={1,0};
        assertArrayEquals(test3,result3);
    }

    @Test
    void fasciaMaterial() {
    }

    @Test
    void roofMaterial() {
    }
    @Test
    void getTotalPrice() throws SQLException, DatabaseException {

        assertEquals(3837.996,calc.getTotalPrice(),0.05);

        //MAX possible length, width, height
        assertEquals(87176.734,calc_max.getTotalPrice(),0.05);

        //MIN possible length, width, height
        assertEquals(2060.676,calc_min.getTotalPrice(),0.05);
    }

    @Test
    void getPartsList() {
    }

}
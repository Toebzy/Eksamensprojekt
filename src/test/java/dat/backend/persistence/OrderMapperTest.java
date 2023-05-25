package dat.backend.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.entities.Partslist;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.entities.User;
import dat.backend.model.persistence.OrderFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest {
    // TODO: Change mysql login credentials if needed below

    private final static String USER = "dev";
    private final static String PASSWORD = "3r!DE32*/fDe";
    private final static String URL = "jdbc:mysql://64.226.113.12:3306/carport_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";
    private static ConnectionPool connectionPool;

    @BeforeAll
    public static void setUpClass() {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);

        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement stmt = testConnection.createStatement()) {
                // Create test database - if not exist
                stmt.execute("CREATE DATABASE  IF NOT EXISTS carport_test;");

                // TODO: Create user table. Add your own tables here
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.user LIKE carport.user;");
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.order LIKE carport.order;");
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.mvariant LIKE carport.mvariant;");
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.orderline LIKE carport.orderline;");
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
                stmt.execute("delete from carport_test.orderline");
                stmt.execute("alter table carport_test.order auto_increment = 1");
                stmt.execute("alter table carport_test.orderline auto_increment = 1");

                // TODO: Insert a few users - insert rows into your own tables here
                stmt.execute("insert into carport_test.user (iduser, email, password,  zipcode, address, name, phonenumber) " +
                        "values ('1','testUser','1234','4200','Lyngby','Morten','112')");
                stmt.execute("insert into carport_test.user (iduser, email, password,  zipcode, address, name, phonenumber) " +
                        "values ('2','testUser2','12345','4200','Lyngby','Norten','1123')");

                stmt.execute("insert into carport_test.order (idorder, status, carportwidth, carportlength, carportheight, price, paymentstatus, iduser) " +
                        "values ('1','processing','1200','600','210','6000','0','1')");
                stmt.execute("insert into carport_test.order (idorder, status, carportwidth, carportlength, carportheight, price, paymentstatus, iduser) " +
                        "values ('2','completed','800','400','120','4000','1','2')");
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
    void testCreateOrdering() throws DatabaseException {
        assertDoesNotThrow(() ->OrderFacade.createOrder(1100,600,200,1,connectionPool));
        String sql = "SELECT * FROM carport_test.order where idorder=3;";
        String sql2 ="SELECT * FROM carport_test.orderline where iditemlist=1;"; //first row out of 8
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    assertEquals("processing",rs.getString("status"));
                    assertEquals("3",rs.getString("idorder"));
                    assertEquals("10753.74",rs.getString("price"));
                    assertEquals("0",rs.getString("paymentstatus"));
                }
            }
            try (PreparedStatement ps2 = connection.prepareStatement(sql2)) {
                ResultSet rs2 = ps2.executeQuery();
                if (rs2.next()) {
                    assertEquals("6",rs2.getString("idmvariant"));
                    assertEquals("97x97 mm.\ttrykimp. Stolpe",rs2.getString("description"));
                    assertEquals("12",rs2.getString("amount"));
                    assertEquals("3",rs2.getString("idorder"));
                }
            }
        }
        catch (SQLException e) {
            throw new DatabaseException(e, "Error creating order. Something went wrong with the database");
        }
    }

    @Test
    void testInfoList() throws DatabaseException {
        List<User> infoList = OrderFacade.infoList(connectionPool);
        List<User> expectedList = new ArrayList<>();
        User user1 = new User("1","testUser","1234","0","4200","Lyngby","Morten","122","0");
        User user2 = new User("2","testUser2","12345","0","4200","Lyngby","Norten","1223","0");
        expectedList.add(user1);
        expectedList.add(user2);
        assertEquals(expectedList,infoList);

    }

    @Test
    void testOrderList() throws DatabaseException {
        List<Order> orderList = OrderFacade.orderList(connectionPool);
        List<Order> expectedList = new ArrayList<>();
        Order order1 = new Order("1","processing","1200","600","210","1","6000.0",false);;
        Order order2 = new Order("2","completed","800","400","120","2","4000.0",true);
        expectedList.add(order1);
        expectedList.add(order2);
        assertEquals(expectedList.toString(),orderList.toString());
    }

    @Test
    void createPartsList() throws SQLException, DatabaseException {
        OrderFacade.createOrder(1100,600,200,1,connectionPool);
        List<Partslist> partsList = OrderFacade.createPartsList("3", connectionPool);
        List<Partslist> expectedList = new ArrayList<>();
        expectedList.add(new Partslist("22","97x97 mm.\ttrykimp. Stolpe","300","12"));
        expectedList.add(new Partslist("20","45x195 mm.\tspærtræ ubh","600","18"));
        expectedList.add(new Partslist("20","45x195 mm.\tspærtræ ubh","600","4"));
        expectedList.add(new Partslist("1","25x200 mm. trykimp.Brædt","360","8"));
        expectedList.add(new Partslist("2","25x200 mm. trykimp.Brædt","540","2"));
        expectedList.add(new Partslist("4","25x125mm. trykimp.Brædt","360","8"));
        expectedList.add(new Partslist("7","25x125mm. trykimp.Brædt","540","2"));
        expectedList.add(new Partslist("30","Plastmo Ecolite blåtonet","600","12"));

        //assertEquals(expectedList.toString(),partsList.toString());
    }

    @Test
    void updateStatus() throws DatabaseException {
        OrderFacade.updateStatus("cancelled","1",connectionPool);
        String sql = "SELECT status FROM carport_test.order where idorder=1;";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    assertEquals("cancelled", rs.getString("status"));
                }
            }
        }
        catch (SQLException e) {
        throw new DatabaseException(e, "Error updating status. Something went wrong with the database");
        }
    }

    @Test
    void getDimensions() throws DatabaseException{
        int[] dimensions = OrderFacade.getDimensions("1",connectionPool);
        assertEquals(1200,dimensions[0]);
        assertEquals(600,dimensions[1]);
        assertEquals(210,dimensions[2]);
    }

    @Test
    void updatePaid() throws DatabaseException {
        OrderFacade.updatePaid("1",true,connectionPool);
        String sql = "SELECT paymentstatus FROM carport_test.order where idorder=1;";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    assertEquals("1", rs.getString("paymentstatus"));
                }
            }
        }
        catch (SQLException e) {
            throw new DatabaseException(e, "Error updating status. Something went wrong with the database");
        }
    }

}


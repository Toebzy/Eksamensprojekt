package dat.backend.model.persistence;

import dat.backend.model.entities.*;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderMapper {

    static List<User> infoList(ConnectionPool connectionpool) throws DatabaseException {
        List<User> userList = new ArrayList<>();
        String sql1 = "SELECT iduser, email, password, balance, zipcode, address, name, phonenumber, role FROM `user`";
        try (Connection connection = connectionpool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql1)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String userid = (rs.getString("iduser"));
                    String email = (rs.getString("email"));
                    String password = (rs.getString("password"));
                    String name = (rs.getString("balance"));
                    String balance = (rs.getString("zipcode"));
                    String zipcode = (rs.getString("address"));
                    String address = (rs.getString("name"));
                    String phonenumber = (rs.getString("phonenumber"));
                    String role = (rs.getString("role"));

                    userList.add(new User(userid, email, password, name, balance, zipcode, address, phonenumber, role));
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error fetching data. Something went wrong with the database");
        }
        return userList;
    }
    static List<Order> orderList(ConnectionPool connectionpool) throws DatabaseException {
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT idorder, status, carportwidth, carportlength,carportheight, iduser, price, paymentstatus FROM `order`";
        try (Connection connection = connectionpool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String idorder = (rs.getString("idorder"));
                    String status = (rs.getString("status"));
                    String carportwidth = (rs.getString("carportwidth"));
                    String carportlength = (rs.getString("carportlength"));
                    String carportheight = (rs.getString("carportheight"));
                    String iduser = (rs.getString("iduser"));
                    String price = (rs.getString("price"));
                    boolean ispaid = (rs.getBoolean("paymentstatus"));
                    orderList.add(new Order(idorder, status, carportwidth, carportlength, carportheight, iduser, price, ispaid));
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error fetching data. Something went wrong with the database");
        }
        return orderList;
    }
    public static void createOrder(int length, int width,int height, int userid, ConnectionPool connectionPool) throws DatabaseException, SQLException {
        Logger.getLogger("web").log(Level.INFO, "Creating new order for: "+userid);
        String sql = "insert into `order` (status, carportwidth, carportlength, carportheight, price, iduser) values (?,?,?,?,?,?)";
        Calculator calc = new Calculator(length,width,height,connectionPool);
        float price = calc.getTotalPrice();
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, "processing");
                ps.setInt(2, width);
                ps.setInt(3, length);
                ps.setInt(4, height);
                ps.setFloat(5, price);
                ps.setInt(6, userid);
                ps.executeUpdate();

                createOrderLine(calc,userid,connectionPool);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Error occurred when creating the order. Something went wrong with the database");
        }
    }
    public static void createOrderLine(Calculator calc, int userid, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT idorder FROM `order` WHERE iduser =? AND status LIKE 'processing'";
        int idorder = 0;
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, userid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    idorder = rs.getInt("idorder");
                }
            }
            Map<Material,Integer> partsList = calc.getPartsList();
            String sql2 ="insert into orderline (idmvariant, description, length, amount, idorder) values (?,?,?,?,?)";
            for (Map.Entry<Material, Integer> entry : partsList.entrySet()) {
                try (PreparedStatement ps = connection.prepareStatement(sql2)) {
                    ps.setInt(1, entry.getKey().getMvariant());
                    ps.setString(2, entry.getKey().getDescription());
                    ps.setInt(3, entry.getKey().getLength());
                    ps.setInt(4, entry.getValue());
                    ps.setInt(5, idorder);
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Error occurred when creating the order. Something went wrong with the database");
        }
    }
    static List<Partslist> createPartsList(String idorder, ConnectionPool connectionpool) throws DatabaseException {
        List<Partslist> partsList = new ArrayList<>();
        String sql = "SELECT idmvariant, description, length, amount FROM orderline WHERE idorder =?";

        try (Connection connection = connectionpool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, idorder);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String idmvariant = (rs.getString("idmvariant"));
                    String description = (rs.getString("description"));
                    String length = (rs.getString("length"));
                    String amount = (rs.getString("amount"));
                    partsList.add(new Partslist(idmvariant, description, length, amount));
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error fetching data. Something went wrong with the database");
        }
        return partsList;
    }
    static void updateStatus(String status, String idorder, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "UPDATE `order` SET status = ? WHERE idorder = ?"; //backticks(``) are needed, since "order" is and SQL Keyword

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, status);
                ps.setString(2, idorder);
                System.out.println(ps);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Error updating order status. Something went wrong with the database");
        }
    }


    public static void updatePaid(String idorder, boolean b, ConnectionPool connectionPool) throws DatabaseException
    {
        String sql = "UPDATE `order` SET paymentstatus = ? WHERE idorder = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setBoolean(1, b);
                ps.setString(2, idorder);
                System.out.println(ps);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Error updating order status. Something went wrong with the database");
        }

    }
}

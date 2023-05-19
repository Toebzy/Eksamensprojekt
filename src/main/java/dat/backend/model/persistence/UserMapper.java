package dat.backend.model.persistence;

import dat.backend.model.entities.Calculator;
import dat.backend.model.entities.Material;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

class UserMapper {
    static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "login attempt from: "+email);
        User user;
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String userid = rs.getString("iduser");
                    String balance = rs.getString("balance");
                    String role = rs.getString("role");
                    String zipcode = rs.getString("zipcode");
                    String address = rs.getString("address");
                    String name = rs.getString("name");
                    String phonenumber = rs.getString("phonenumber");
                    user = new User(userid, email, password, balance, zipcode, address, name, phonenumber, role);
                } else {
                    throw new DatabaseException("Wrong email or password");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
        return user;
    }

    static void createUser(String email, String password, String zipcode, String address, String name, String phonenumber, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        String sql = "insert into carport.user (email, password, zipcode, address, name, phonenumber) values (?,?,?,?,?,?)";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, password);
                ps.setString(3, zipcode);
                ps.setString(4, address);
                ps.setString(5, name);
                ps.setString(6, phonenumber);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected != 1) {
                    throw new DatabaseException("The user with email = " + email + " could not be inserted into the database");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not insert user into database");
        }
    }

    static boolean checkEmail(String email, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM carport.user WHERE email = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                System.out.println(ps);
                if (rs.next()) {
                    System.out.println("true");
                    return true;
                } else {
                    System.out.println("false");
                    return false;
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
    }

    static boolean checkZip(String zipcode, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM carport.user WHERE zipcode = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, zipcode);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
    }

    static List<User> infoList(ConnectionPool connectionpool) throws DatabaseException {
        List<User> userList = new ArrayList<>();
        String sql1 = "SELECT iduser, email, password, balance, zipcode, address, name, phonenumber, role FROM carport.user";
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
        String sql1 = "SELECT idorder, status, carportwidth, carportlength, iduser FROM carport.order";
        try (Connection connection = connectionpool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql1)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String idorder = (rs.getString("idorder"));
                    String status = (rs.getString("status"));
                    String carportwidth = (rs.getString("carportwidth"));
                    String carportlength = (rs.getString("carportlength"));
                    String iduser = (rs.getString("iduser"));
                    orderList.add(new Order(idorder, status, carportwidth, carportlength, iduser));
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error fetching data. Something went wrong with the database");
        }
        return orderList;
    }

    static void balanceChange(String balance, String userid, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "UPDATE user SET balance = ? WHERE iduser = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, balance);
                ps.setString(2, userid);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Error fetching data. Something went wrong with the database");
        }
    }

    public static void createOrder(int length, int width, int userid, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "insert into carport.order (status, carportwidth, carportlength, price, iduser) values (?,?,?,?,?)";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, "processing");
                ps.setInt(2, width);
                ps.setInt(3, length);
                ps.setInt(4, 0);
                ps.setInt(5, userid);
                ps.executeUpdate();

                createOrderLine(length,width,userid,connectionPool);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Error occurred when creating the order. Something went wrong with the database");
        }
    }
    public static void createOrderLine(int length, int width, int userid, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT idorder FROM carport.order WHERE iduser =? AND status LIKE 'processing'";
        int idorder = 0;
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, userid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    idorder = rs.getInt("idorder");
                }
            }
                Calculator calc = new Calculator(length,width,connectionPool);
                Map<Material,Integer> partsList = calc.getPartsList();
                String sql2 ="insert into carport.orderline (idmvariant, description, length, amount, idorder) values (?,?,?,?,?)";
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

    static void updateStatus(String status, String idorder, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "UPDATE carport.order SET status = ? WHERE idorder = ?";

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
}
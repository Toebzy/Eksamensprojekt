package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
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
        Logger.getLogger("web").log(Level.INFO, "Creating new user for: "+email);
        if(checkEmail(email,connectionPool)){
            throw new DatabaseException("The user with email = " + email + " could not be inserted into the database");
        }
        String sql = "insert into user (email, password, zipcode, address, name, phonenumber) values (?,?,?,?,?,?)";

            try (Connection connection = connectionPool.getConnection()) {
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setString(1, email);
                    ps.setString(2, password);
                    ps.setString(3, zipcode);
                    ps.setString(4, address);
                    ps.setString(5, name);
                    ps.setString(6, phonenumber);
                    ps.executeUpdate();
                }
            } catch (SQLException ex) {
                throw new DatabaseException(ex, "Could not insert user into database");
            }


    }

     static boolean checkEmail(String email, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM user WHERE email = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error checking for email. Something went wrong with the database");
        }
    }


    static boolean checkZip(String zipcode, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM city WHERE zipcode = ?";
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
            throw new DatabaseException(ex, "Error checking zipcode. Something went wrong with the database");
        }
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
            throw new DatabaseException(e, "Error changing balance. Something went wrong with the database");
        }
    }

}
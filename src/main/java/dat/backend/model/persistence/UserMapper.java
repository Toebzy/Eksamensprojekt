package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class UserMapper
{
    static User login(String username, String password, ConnectionPool connectionPool) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");

        User user = null;

        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    String role = rs.getString("role");
                    String zipcode = rs.getString("zipcode");
                    String address = rs.getString("address");
                    String name = rs.getString("name");
                    String phonenumber = rs.getString("phonenumber");
                    user = new User(username, password, role, zipcode, address, name, phonenumber);
                } else
                {
                    throw new DatabaseException("Wrong email or password");
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
        return user;
    }

    static User createUser(String email, String password, String zipcode, String address, String name, String phonenumber, ConnectionPool connectionPool) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");
        User user;
        String sql = "insert into carport.user (email, password, zipcode, address, name, phonenumber) values (?,?,?,?,?,?)";
        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, email);
                ps.setString(2, password);
                ps.setString(3, zipcode);
                ps.setString(4, address);
                ps.setString(5, name);
                ps.setString(6, phonenumber);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1)
                {
                    user = new User(email, password, "0", zipcode, address, name, phonenumber);
                } else
                {
                    throw new DatabaseException("The user with email = " + email + " could not be inserted into the database");
                }
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Could not insert user into database");
        }
        return user;
    }
    public static boolean checkEmail(String email, ConnectionPool connectionPool) throws DatabaseException
    {

        String sql = "SELECT * FROM carport.user WHERE email = ?";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                System.out.println(ps);
                if (rs.next())
                {
                    System.out.println("true");
                    return true;
                } else
                {
                    System.out.println("false");
                    return false;
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
    }
    public static boolean checkZip(String zipcode, ConnectionPool connectionPool) throws DatabaseException
    {
        String sql = "SELECT * FROM carport.user WHERE zipcode = ?";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, zipcode);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    return true;
                } else
                {
                    return false;
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
    }
    public static List<List> infoList(ConnectionPool connectionpool) throws DatabaseException
    {
        List<List> list = new ArrayList<>();
        List<String> arrayid = new ArrayList<>();
        List<String> arrayemail = new ArrayList<>();
        List<String> arraypassword = new ArrayList<>();
        List<String> arrayname = new ArrayList<>();
        List<String> arraybalance = new ArrayList<>();
        List<String> arrayzipcode= new ArrayList<>();
        List<String> arrayaddress = new ArrayList<>();
        List<String> arrayphonenumber = new ArrayList<>();
        list.add(arrayid);
        list.add(arrayemail);
        list.add(arraypassword);
        list.add(arrayname);
        list.add(arraybalance);
        list.add(arrayzipcode);
        list.add(arrayaddress);
        list.add(arrayphonenumber);

        String sql1 = "SELECT iduser, email, password, name, balance, zipcode, address, phonenumber FROM carport.user";

        try (Connection connection = connectionpool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql1))
            {

                ResultSet rs = ps.executeQuery();

                while (rs.next())
                {
                    arrayid.add(rs.getString("iduser"));
                    arrayemail.add(rs.getString("email"));
                    arraypassword.add(rs.getString("password"));
                    arrayname.add(rs.getString("balance"));
                    arraybalance.add(rs.getString("zipcode"));
                    arrayzipcode.add(rs.getString("address"));
                    arrayaddress.add(rs.getString("name"));
                    arrayphonenumber.add(rs.getString("phonenumber"));
                }
            }
        }catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
        return list;
    }
}
package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class UserFacade
{
    public static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException
    {
        return UserMapper.login(email, password, connectionPool);
    }
    public static void createUser(String email, String password, String zipcode, String address, String name, String phonenumber, ConnectionPool connectionPool) throws DatabaseException
    {
       UserMapper.createUser(email, password, zipcode, address, name, phonenumber, connectionPool);
    }
    public static boolean checkEmail(String email, ConnectionPool connectionPool) throws DatabaseException {
        return UserMapper.checkEmail(email, connectionPool);
    }

    public static boolean checkZip(String zipcode, ConnectionPool connectionPool)  throws DatabaseException{
        return UserMapper.checkZip(zipcode,connectionPool);
    }
    public static List<User> infoList(ConnectionPool connectionPool) throws DatabaseException {
        return UserMapper.infoList(connectionPool);
    }
    public static List<Order> orderList(ConnectionPool connectionpool) throws DatabaseException{
        return UserMapper.orderList(connectionpool);
    }
    public static void balanceChange(String balance, String userid, ConnectionPool connectionPool) throws DatabaseException {
        UserMapper.balanceChange(balance, userid, connectionPool);
    }

    public static void createOrder(int length, int width, int userid, ConnectionPool connectionPool) throws DatabaseException {
        UserMapper.createOrder(length,width,userid,connectionPool);
    }
}
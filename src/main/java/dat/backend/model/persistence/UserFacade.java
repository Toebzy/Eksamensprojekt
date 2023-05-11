package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
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
    public static List infoList(ConnectionPool connectionPool) throws DatabaseException {
        return UserMapper.infoList(connectionPool);
    }
    public static void balanceChange(String balance, String userid, ConnectionPool connectionPool) throws DatabaseException {
        UserMapper.balanceChange(balance, userid, connectionPool);
    }
}
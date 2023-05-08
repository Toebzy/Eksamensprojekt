package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

public class UserFacade
{
    public static User login(String username, String password, ConnectionPool connectionPool) throws DatabaseException
    {
        return UserMapper.login(username, password, connectionPool);
    }

    public static User createUser(String email, String password,String zipcode, String address, String name, String phonenumber, ConnectionPool connectionPool) throws DatabaseException
    {
        return UserMapper.createUser(email, password, zipcode, address, name, phonenumber, connectionPool);
    }

    public static boolean checkEmail(String email, ConnectionPool connectionPool) throws DatabaseException {
        return UserMapper.checkEmail(email, connectionPool);
    }

    public static boolean checkZip(String zipcode, ConnectionPool connectionPool)  throws DatabaseException{
        return UserMapper.checkZip(zipcode,connectionPool);
    }
}

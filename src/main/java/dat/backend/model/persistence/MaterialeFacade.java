package dat.backend.model.persistence;

import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;

public class MaterialeFacade
{

    public static Double getPrice(int mvariant, Connection connection) throws DatabaseException
    {
       return MaterialeMapper.getPrice(mvariant, connection);
    }

    public static int getLength(int mvariant, Connection connection) throws DatabaseException
    {
        return MaterialeMapper.getLength(mvariant, connection);
    }

    public static String getUnit(int mvariant, Connection connection) throws DatabaseException
    {
        return MaterialeMapper.getUnit(mvariant, connection);
    }

    public static String getDescription(int mvariant, Connection connection) throws DatabaseException
    {
        return MaterialeMapper.getDescription(mvariant, connection);
    }
}

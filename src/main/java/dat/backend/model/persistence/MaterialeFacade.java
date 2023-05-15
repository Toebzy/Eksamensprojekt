package dat.backend.model.persistence;

import dat.backend.model.exceptions.DatabaseException;

public class MaterialeFacade
{

    public static Double getPrice(int mvariant, ConnectionPool connectionPool) throws DatabaseException
    {
       return MaterialeMapper.getPrice(mvariant, connectionPool);
    }

    public static int getLength(int mvariant, ConnectionPool connectionPool) throws DatabaseException
    {
        return MaterialeMapper.getLength(mvariant, connectionPool);
    }

    public static String getUnit(int mvariant, ConnectionPool connectionPool) throws DatabaseException
    {
        return MaterialeMapper.getUnit(mvariant, connectionPool);
    }

    public static String getDescription(int mvariant, ConnectionPool connectionPool) throws DatabaseException
    {
        return MaterialeMapper.getDescription(mvariant, connectionPool);
    }
}

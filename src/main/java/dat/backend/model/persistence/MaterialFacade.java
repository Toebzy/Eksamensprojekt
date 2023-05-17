package dat.backend.model.persistence;

import dat.backend.model.entities.Material;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;

public class MaterialFacade
{
    public static Double getPrice(int mvariant, Connection connection) throws DatabaseException
    {
       return MaterialMapper.getPrice(mvariant, connection);
    }

    public static int getLength(int mvariant, Connection connection) throws DatabaseException
    {
        return MaterialMapper.getLength(mvariant, connection);
    }

    public static String getUnit(int mvariant, Connection connection) throws DatabaseException
    {
        return MaterialMapper.getUnit(mvariant, connection);
    }

    public static String getDescription(int mvariant, Connection connection) throws DatabaseException
    {
        return MaterialMapper.getDescription(mvariant, connection);
    }
}

package dat.backend.model.persistence;

import dat.backend.model.entities.Material;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.util.List;

public class MaterialFacade{

    public static List<Material> getMaterialById(int id, ConnectionPool connectionPool)throws DatabaseException {
        return MaterialMapper.getMaterialById(id, connectionPool);
    }
    public static Double getPrice(int mvariant, ConnectionPool connectionPool) throws DatabaseException
    {
       return MaterialMapper.getPrice(mvariant, connectionPool);
    }

    public static int getLength(int mvariant, ConnectionPool connectionPool) throws DatabaseException
    {
        return MaterialMapper.getLength(mvariant, connectionPool);
    }

    public static String getUnit(int mvariant, ConnectionPool connectionPool) throws DatabaseException
    {
        return MaterialMapper.getUnit(mvariant, connectionPool);
    }

    public static String getDescription(int mvariant, ConnectionPool connectionPool) throws DatabaseException
    {
        return MaterialMapper.getDescription(mvariant, connectionPool);
    }
}

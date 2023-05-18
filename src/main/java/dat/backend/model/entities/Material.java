package dat.backend.model.entities;

import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.MaterialFacade;

import java.sql.Connection;

public class Material {
    int mvariant;
    int length;
    double price;
    String unit;
    String description;

    public Material(int mvariant, int length, double price, String unit, String description)
    {
        this.mvariant = mvariant;
        this.length = length;
        this.price = price;
        this.unit = unit;
        this.description = description;
    }

    public int getMvariant()
    {
        return mvariant;
    }

    public int getLength()
    {
        return length;
    }

    public double getPrice()
    {
        return price;
    }

    public String getUnit()
    {
        return unit;
    }

    public String getDescription()
    {
        return description;
    }

    public static Material newMaterial(int mvariant, ConnectionPool connectionPool)
    {
        Material material;
        try (Connection connection = connectionPool.getConnection())
        {
            int length = MaterialFacade.getLength(mvariant, connection);
            double price = MaterialFacade.getPrice(mvariant, connection);
            String unit = MaterialFacade.getUnit(mvariant, connection);
            String description = MaterialFacade.getDescription(mvariant, connection);
            material = new Material(mvariant, length, price, unit, description);
            return material;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

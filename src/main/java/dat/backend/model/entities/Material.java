package dat.backend.model.entities;

import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.MaterialFacade;


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

    public String getDescription()
    {
        return description;
    }

    public static Material newMaterial(int mvariant, ConnectionPool connectionPool)
    {
        Material material;
    try
        {
            int length = MaterialFacade.getLength(mvariant, connectionPool);
            double price = MaterialFacade.getPrice(mvariant, connectionPool);
            String unit = MaterialFacade.getUnit(mvariant, connectionPool);
            String description = MaterialFacade.getDescription(mvariant, connectionPool);
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

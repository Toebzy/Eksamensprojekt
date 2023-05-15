package dat.backend.model.entities;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.MaterialeFacade;

public class Materiale
{
    private static ConnectionPool connectionPool = ApplicationStart.getConnectionPool();
    int mvariant;
    double length;
    double price;
    String unit;
    String description;

    Materiale(int mvariant, int length)
    {
        this.mvariant = mvariant;
        this.length = length;
    }
    Materiale(int mvariant, int length, double price, String unit, String description)
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

    public double getLength()
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

    public static Materiale newMateriale(int mvariant)
    {
        Materiale materiale;
        try
        {
            int length = MaterialeFacade.getLength(mvariant, connectionPool);
            double price = MaterialeFacade.getPrice(mvariant, connectionPool);
            String unit = MaterialeFacade.getUnit(mvariant, connectionPool);
            String description = MaterialeFacade.getDescription(mvariant, connectionPool);
            materiale = new Materiale(mvariant, length, price, unit, description);
            return materiale;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

package dat.backend.model.entities;

public class Materiale
{
    int mvariant;
    double length;

    Materiale(int mvariant, int length)
    {
        this.mvariant = mvariant;
        this.length = length;
    }

    public int getMvariant()
    {
        return mvariant;
    }

    public double getLength()
    {
        return length;
    }
    public Materiale newMateriale(int mvariant, int længde)
    {
        return null;
    }
}

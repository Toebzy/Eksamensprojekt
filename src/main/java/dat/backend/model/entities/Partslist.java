package dat.backend.model.entities;

public class Partslist {
    String Mvariant;
    String description;
    String length;
    String amount;

    public Partslist(String mVariant, String description, String length, String amount) {
        this.Mvariant = mVariant;
        this.description = description;
        this.length = length;
        this.amount = amount;
    }

    public String getMvariant() {
        return Mvariant;
    }
    public String getDescription() {
        return description;
    }
    public String getLength() {
        return length;
    }
    public void setLength(String length) {
        this.length = length;
    }
    public String getAmount() {
        return amount;
    }


    @Override
    public String toString() {
        return "Partslist{" +
                "mVariant='" + Mvariant + '\'' +
                ", description='" + description + '\'' +
                ", length='" + length + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}

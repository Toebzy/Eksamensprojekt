package dat.backend.model.entities;

public class Partslist {
    String mVariant;
    String description;
    String length;
    String amount;

    public Partslist(String mVariant, String description, String length, String amount) {
        this.mVariant = mVariant;
        this.description = description;
        this.length = length;
        this.amount = amount;
    }

    public String getmVariant() {
        return mVariant;
    }

    public void setmVariant(String mVariant) {
        this.mVariant = mVariant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Partslist{" +
                "mVariant='" + mVariant + '\'' +
                ", description='" + description + '\'' +
                ", length='" + length + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}

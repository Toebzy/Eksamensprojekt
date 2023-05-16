package dat.backend.model.entities;

public class Order {

    String idorder;
    String status;
    String carportwidth;
    String carportlength;
    String iduser;

    public Order(String idorder, String status, String carportwidth, String carportlength, String iduser){
    this.idorder=idorder;
    this.status=status;
    this.carportwidth=carportwidth;
    this.carportlength=carportlength;
    this.iduser=iduser;
    }

    public String getIdorder() {
        return idorder;
    }
    public String getStatus() {
        return status;
    }
    public String getCarportwidth() {
        return carportwidth;
    }
    public String getCarportlength() {
        return carportlength;
    }
    public String getIduser() {
        return iduser;
    }

    @Override
    public String toString() {
        return "Order{" +
                "idorder='" + idorder + '\'' +
                ", status='" + status + '\'' +
                ", carportwidth='" + carportwidth + '\'' +
                ", carportlength='" + carportlength + '\'' +
                ", iduser='" + iduser + '\'' +
                '}';
    }
}

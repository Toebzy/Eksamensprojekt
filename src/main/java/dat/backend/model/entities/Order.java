package dat.backend.model.entities;

public class Order {

    String idorder;
    String status;
    String carportwidth;
    String carportheight;
    String carportlength;
    String iduser;

    public Order(String idorder, String status, String carportwidth, String carportheight, String carportlength, String iduser){
    this.idorder=idorder;
    this.status=status;
    this.carportwidth=carportwidth;
    this.carportheight=carportheight;
    this.carportlength=carportlength;
    this.iduser=iduser;
    }


    public String getIdorder() {
        return idorder;
    }

    public void setIdorder(String idorder) {
        this.idorder = idorder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCarportwidth() {
        return carportwidth;
    }

    public void setCarportwidth(String carportwidth) {
        this.carportwidth = carportwidth;
    }

    public String getCarportheight() {
        return carportheight;
    }

    public void setCarportheight(String carportheight) {
        this.carportheight = carportheight;
    }

    public String getCarportlength() {
        return carportlength;
    }

    public void setCarportlength(String carportlength) {
        this.carportlength = carportlength;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
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

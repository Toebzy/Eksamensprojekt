package dat.backend.model.entities;
public class Order {

    String idorder;
    String status;
    String carportwidth;
    String carportlength;
    String carportheight;
    String iduser;
    String price;
    Boolean isPaid;

    public Order(String idorder, String status, String carportwidth, String carportlength, String carportheight, String iduser, String price, boolean isPaid){
    this.idorder=idorder;
    this.status=status;
    this.carportwidth=carportwidth;
    this.carportheight=carportheight;
    this.carportlength=carportlength;

    this.iduser=iduser;
    this.price=price;
    this.isPaid=isPaid;
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
    public String getCarportheight() {
        return carportheight;
    }
    public String getIduser() {
        return iduser;
    }
    public String getPrice() {
        return price;
    }
    public Boolean getPaid() {
        return isPaid;
    }


    @Override
    public String toString() {
        return "Order{" +
                "idorder='" + idorder + '\'' +
                ", status='" + status + '\'' +
                ", carportwidth='" + carportwidth + '\'' +
                ", carportlength='" + carportlength + '\'' +
                ", carportheight='" + carportheight + '\'' +
                ", iduser='" + iduser + '\'' +
                ", price='" + price + '\'' +
                ", isPaid=" + isPaid +
                '}';
    }
}

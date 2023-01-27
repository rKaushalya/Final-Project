package lk.ijse.finalProject.view.tdm;

public class RoomTDM {
    private String id;
    private String type;
    private String ac;
    private Double price;
    private String availability;

    public RoomTDM() {
    }

    public RoomTDM(String id, String type, String ac, Double price, String availability) {
        this.id = id;
        this.type = type;
        this.ac = ac;
        this.price = price;
        this.availability = availability;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "RoomTDM{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", ac='" + ac + '\'' +
                ", price=" + price +
                ", availability='" + availability + '\'' +
                '}';
    }
}

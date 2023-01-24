package lk.ijse.finalProject.entity;

public class RoomEntity {
    private String rId;
    private String type;
    private String acNonAc;
    private double price;
    private String availability;

    public RoomEntity() {
    }

    public RoomEntity(String rId, String type, String acNonAc, double price, String availability) {
        this.rId = rId;
        this.type = type;
        this.acNonAc = acNonAc;
        this.price = price;
        this.availability = availability;
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAcNonAc() {
        return acNonAc;
    }

    public void setAcNonAc(String acNonAc) {
        this.acNonAc = acNonAc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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
        return "RoomEntity{" +
                "rId='" + rId + '\'' +
                ", type='" + type + '\'' +
                ", acNonAc='" + acNonAc + '\'' +
                ", price=" + price +
                ", availability='" + availability + '\'' +
                '}';
    }
}

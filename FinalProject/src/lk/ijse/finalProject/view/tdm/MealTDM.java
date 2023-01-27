package lk.ijse.finalProject.view.tdm;

public class MealTDM {
    private String id;
    private String name;
    private String availableTime;
    private Double price;

    public MealTDM() {
    }

    public MealTDM(String id, String name, String availableTime, Double price) {
        this.id = id;
        this.name = name;
        this.availableTime = availableTime;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(String availableTime) {
        this.availableTime = availableTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MealTDM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", availableTime='" + availableTime + '\'' +
                ", price=" + price +
                '}';
    }
}

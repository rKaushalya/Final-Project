package lk.ijse.finalProject.entity;

public class MealEntity {
    private String mId;
    private String name;
    private String availableTime;
    private double price;

    public MealEntity() {
    }

    public MealEntity(String mId, String name, String availableTime, double price) {
        this.mId = mId;
        this.name = name;
        this.availableTime = availableTime;
        this.price = price;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MealEntity{" +
                "mId='" + mId + '\'' +
                ", name='" + name + '\'' +
                ", availableTime='" + availableTime + '\'' +
                ", price=" + price +
                '}';
    }
}

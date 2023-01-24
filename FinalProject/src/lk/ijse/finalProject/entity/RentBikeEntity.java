package lk.ijse.finalProject.entity;

public class RentBikeEntity {
    private String regNo;
    private String model;
    private String availability;
    private double pricePerDay;

    public RentBikeEntity() {
    }

    public RentBikeEntity(String regNo, String model, String availability, double pricePerDay) {
        this.regNo = regNo;
        this.model = model;
        this.availability = availability;
        this.pricePerDay = pricePerDay;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    @Override
    public String toString() {
        return "RentBikeEntity{" +
                "regNo='" + regNo + '\'' +
                ", model='" + model + '\'' +
                ", availability='" + availability + '\'' +
                ", pricePerDay=" + pricePerDay +
                '}';
    }
}

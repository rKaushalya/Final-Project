package lk.ijse.finalProject.dto;

public class BikeDTO {
    private String regNo;
    private String model;
    private String availability;
    private Double pricePerDay;

    public BikeDTO(){

    }

    public BikeDTO(String regNo, String model, String availability, Double pricePerDay){
        this.regNo =regNo;
        this.model=model;
        this.availability=availability;
        this.pricePerDay=pricePerDay;
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

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}

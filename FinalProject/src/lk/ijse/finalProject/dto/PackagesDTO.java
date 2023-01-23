package lk.ijse.finalProject.dto;

public class PackagesDTO {
    private String id;
    private String name;
    private Double price;
    private String include;

    public PackagesDTO(String id, String name, Double price, String include) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.include = include;
    }

    public PackagesDTO() {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getInclude() {
        return include;
    }

    public void setInclude(String include) {
        this.include = include;
    }
}

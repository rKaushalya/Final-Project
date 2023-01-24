package lk.ijse.finalProject.entity;

public class PackageEntity {
    private String pkgId;
    private String pkgName;
    private double price;
    private String include;

    public PackageEntity() {
    }

    public PackageEntity(String pkgId, String pkgName, double price, String include) {
        this.pkgId = pkgId;
        this.pkgName = pkgName;
        this.price = price;
        this.include = include;
    }

    public String getPkgId() {
        return pkgId;
    }

    public void setPkgId(String pkgId) {
        this.pkgId = pkgId;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInclude() {
        return include;
    }

    public void setInclude(String include) {
        this.include = include;
    }
}

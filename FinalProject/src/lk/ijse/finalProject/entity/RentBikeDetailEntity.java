package lk.ijse.finalProject.entity;

public class RentBikeDetailEntity {
    private String regNo;
    private String cId;

    public RentBikeDetailEntity() {
    }

    public RentBikeDetailEntity(String regNo, String cId) {
        this.regNo = regNo;
        this.cId = cId;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    @Override
    public String toString() {
        return "RentBikeDetailEntity{" +
                "regNo='" + regNo + '\'' +
                ", cId='" + cId + '\'' +
                '}';
    }
}

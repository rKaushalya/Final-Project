package lk.ijse.finalProject.entity;

public class RoomDetailEntity {
    private String rId;
    private String cId;
    private String date;
    private int dayCount;

    public RoomDetailEntity() {
    }

    public RoomDetailEntity(String rId, String cId, String date, int dayCount) {
        this.rId = rId;
        this.cId = cId;
        this.date = date;
        this.dayCount = dayCount;
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    @Override
    public String toString() {
        return "RoomDetailEntity{" +
                "rId='" + rId + '\'' +
                ", cId='" + cId + '\'' +
                ", date='" + date + '\'' +
                ", dayCount=" + dayCount +
                '}';
    }
}

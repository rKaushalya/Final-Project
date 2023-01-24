package lk.ijse.finalProject.entity;

public class OrdersEntity {
    private String orderId;
    private String date;
    private int stayDayCount;
    private String rId;
    private String pkgId;
    private String regNo;
    private int bikeDayCount;

    public OrdersEntity() {
    }

    public OrdersEntity(String orderId, String date, int stayDayCount, String rId, String pkgId, String regNo, int bikeDayCount) {
        this.orderId = orderId;
        this.date = date;
        this.stayDayCount = stayDayCount;
        this.rId = rId;
        this.pkgId = pkgId;
        this.regNo = regNo;
        this.bikeDayCount = bikeDayCount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStayDayCount() {
        return stayDayCount;
    }

    public void setStayDayCount(int stayDayCount) {
        this.stayDayCount = stayDayCount;
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public String getPkgId() {
        return pkgId;
    }

    public void setPkgId(String pkgId) {
        this.pkgId = pkgId;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public int getBikeDayCount() {
        return bikeDayCount;
    }

    public void setBikeDayCount(int bikeDayCount) {
        this.bikeDayCount = bikeDayCount;
    }

    @Override
    public String toString() {
        return "OrdersEntity{" +
                "orderId='" + orderId + '\'' +
                ", date='" + date + '\'' +
                ", stayDayCount=" + stayDayCount +
                ", rId='" + rId + '\'' +
                ", pkgId='" + pkgId + '\'' +
                ", regNo='" + regNo + '\'' +
                ", bikeDayCount=" + bikeDayCount +
                '}';
    }
}

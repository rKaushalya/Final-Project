package lk.ijse.finalProject.dto;

public class OrderDetailDTO {
    private String orderId;
    private String date;
    private int roomDayCount;
    private String rId;
    private String pkgId;
    private String regNo;
    private int bikeDayCount;
    private Double total;
    private Double recevedAmount;
    private Double balance;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String orderId, String date, int roomDayCount, String rId, Double total, Double recevedAmount, Double balance) {
        this.orderId = orderId;
        this.date = date;
        this.roomDayCount = roomDayCount;
        this.rId = rId;
        this.total = total;
        this.recevedAmount = recevedAmount;
        this.balance = balance;
    }

    public OrderDetailDTO(String orderId, String date, int roomDayCount, String rId, String pkgId, String regNo, int bikeDayCount, Double total, Double receverdAmount, Double balance) {
        this.orderId = orderId;
        this.date = date;
        this.roomDayCount = roomDayCount;
        this.rId = rId;
        this.pkgId = pkgId;
        this.regNo = regNo;
        this.bikeDayCount = bikeDayCount;
        this.total = total;
        this.recevedAmount = receverdAmount;
        this.balance = balance;
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

    public int getRoomDayCount() {
        return roomDayCount;
    }

    public void setRoomDayCount(int roomDayCount) {
        this.roomDayCount = roomDayCount;
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getReceverdAmount() {
        return recevedAmount;
    }

    public void setReceverdAmount(Double receverdAmount) {
        this.recevedAmount = receverdAmount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}

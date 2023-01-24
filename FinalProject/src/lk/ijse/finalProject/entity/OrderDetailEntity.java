package lk.ijse.finalProject.entity;

public class OrderDetailEntity {
    private String cId;
    private String orderId;

    public OrderDetailEntity() {
    }

    public OrderDetailEntity(String cId, String orderId) {
        this.cId = cId;
        this.orderId = orderId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderDetailEntity{" +
                "cId='" + cId + '\'' +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}

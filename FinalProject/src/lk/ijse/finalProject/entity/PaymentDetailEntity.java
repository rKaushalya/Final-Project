package lk.ijse.finalProject.entity;

public class PaymentDetailEntity {
    private String orderId;
    private String date;
    private double recevedAmount;
    private double balance;
    private double total;

    public PaymentDetailEntity() {
    }

    public PaymentDetailEntity(String orderId, String date, double recevedAmount, double balance, double total) {
        this.orderId = orderId;
        this.date = date;
        this.recevedAmount = recevedAmount;
        this.balance = balance;
        this.total = total;
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

    public double getRecevedAmount() {
        return recevedAmount;
    }

    public void setRecevedAmount(double recevedAmount) {
        this.recevedAmount = recevedAmount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "PaymentDetailEntity{" +
                "orderId='" + orderId + '\'' +
                ", date='" + date + '\'' +
                ", recevedAmount=" + recevedAmount +
                ", balance=" + balance +
                ", total=" + total +
                '}';
    }
}

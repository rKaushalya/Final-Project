package lk.ijse.finalProject.to;

public class Employee {
    private String id;
    private String name;
    private String address;
    private String contact;
    private String nic;

    public Employee(){

    }

    public Employee(String id,String name,String address,String contact,String nic){
        this.id=id;
        this.name=name;
        this.address=address;
        this.contact=contact;
        this.nic=nic;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }
}

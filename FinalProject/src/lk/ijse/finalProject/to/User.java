package lk.ijse.finalProject.to;

public class User {
    private String userId;
    private String userName;
    private String password;
    private String email;
    private String role;

    public User(){

    }

    public User(String userId,String userName,String password,String email){
        this.userId=userId;
        this.userName=userName;
        this.password=password;
        this.email=email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

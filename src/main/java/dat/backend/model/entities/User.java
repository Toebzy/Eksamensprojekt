package dat.backend.model.entities;

import java.util.Objects;

public class User
{

    private String userid;
    private String email;
    private String password;
    private String balance;
    private String zipcode;
    private String address;
    private String name;
    private String phonenumber;
    private String role;


    public User(String userid, String email, String password, String balance, String zipcode, String address, String name, String phonenumber, String role)
    {
        this.userid = userid;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.zipcode = zipcode;
        this.address = address;
        this.name = name;
        this.phonenumber = phonenumber;
        this.role = role;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getUsername()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getRole()
    {
        return role;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUsername().equals(user.getUsername()) && getPassword().equals(user.getPassword()) &&
                getRole().equals(user.getRole());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getUsername(), getPassword(), getRole());
    }

    @Override
    public String toString() {
        return "User{" +
                "userid='" + userid + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", balance='" + balance + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

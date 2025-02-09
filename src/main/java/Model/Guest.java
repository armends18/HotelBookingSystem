package Model;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;

public class Guest implements Serializable {
    @Serial
    private static final long serialVersionUID = -4768914987768159955L;
    private String name;
    private BigInteger phoneNumber;
    private String email;
    private String username;
    private String password;
    private LocalDate date1;
    private LocalDate date2;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public Guest(String name,String phoneNumber,String email,String username,String password) {
        this.name = name;
        this.phoneNumber = new BigInteger(phoneNumber) ;
        this.email = email;
        this.date1 = null;
        this.date2 = null;
        this.username=username;
        this.password=password;

    }
    public Guest(String name, String phoneNumber,String email, LocalDate date1, LocalDate date2) {
        this.name = name;
        this.email = email;
        this.phoneNumber = new BigInteger(phoneNumber) ;
        this.date1 = date1;
        this.date2 = date2;
    }

    public String getName() {
        return name;
    }

    public BigInteger getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDate1() {
        return date1;
    }

    public LocalDate getDate2() {
        return date2;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(BigInteger phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setDate1(LocalDate date1) {
        this.date1 = date1;
    }

    public void setDate2(LocalDate date2) {
        this.date2 = date2;
    }

}

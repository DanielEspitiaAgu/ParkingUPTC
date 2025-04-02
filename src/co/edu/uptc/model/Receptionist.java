package co.edu.uptc.model;

import java.time.LocalDateTime;

public class Receptionist extends User {
    private String phone;

    public Receptionist(String idNumber, String email, String password, String name, String lastName, String phone) {
        super(idNumber, email, password, name, lastName);
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
   
}

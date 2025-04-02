package co.edu.uptc.model;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Admin extends User {
    private LocalDateTime lastLogin;
    public Admin(String idNumber, String email, String password, String name, String lastName) {
        super(idNumber, email, password, name, lastName);
    }
    
}

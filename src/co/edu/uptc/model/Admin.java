package co.edu.uptc.model;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Admin extends User {
    private LocalDateTime lastLogin;
    public Admin(String idNumber, String email, String password, String name, String lastName) {
        super(idNumber, email, password, name, lastName);
    }

    public String getLastLogin() {
        return lastLogin.toString();
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return "Administrador:\n" +
        "  ID: " + getIdNumber() + ",\n" +
        "  Correo Electrónico: " + getEmail() + ",\n" +
        "  Nombre: " + getName() + ",\n" +
        "  Apellido: " + getLastName() + "\n"+
        "  Último ingreso: " + getLastLogin() + "\n";

    }
    
}

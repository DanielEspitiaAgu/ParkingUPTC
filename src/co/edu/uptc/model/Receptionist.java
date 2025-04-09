package co.edu.uptc.model;

import java.time.LocalDateTime;

public class Receptionist extends User {
    private String phone;
    private String adress;

    public Receptionist(String idNumber, String email, String password, String name, String lastName, String phone, String adress) {
        super(idNumber, email, password, name, lastName);
        this.phone = phone;
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "Recepcionista:\n" +
               "  Nombre: " + getName() + ",\n" +
               "  Apellido: " + getLastName() + ",\n" +
               "  ID: " + getIdNumber() + ",\n" +
               "  Correo Electrónico: " + getEmail() + ",\n" +
               "  Teléfono: " + phone + ",\n" +
               "  Dirección: " + adress + "\n";
    }
   
}

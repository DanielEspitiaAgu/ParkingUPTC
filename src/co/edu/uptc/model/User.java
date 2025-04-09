package co.edu.uptc.model;

public class User {
    private String idNumber;
    private String email;
    private String password;
    private String name;
    private String lastName;

    public User(String idNumber, String email, String password, String name, String lastName) {
        this.idNumber = idNumber;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String userName) {
        this.idNumber = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    } 

    @Override
    public String toString() {
        return "Usuario:\n" +
                "  ID: " + idNumber + ",\n" +
                "  Correo Electrónico: " + email + ",\n" +
                "  Nombre: " + name + ",\n" +
                "  Apellido: " + lastName + "\n";
    }

}

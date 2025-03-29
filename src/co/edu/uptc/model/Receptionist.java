package co.edu.uptc.model;

public class Receptionist extends User {
    private String name;
    private String lastName;
    private String phone;
    private Parking parking;

    public Receptionist(String userName, String email, String password, String name, String lastName, String phone, Parking parking) {
        super(userName, email, password);
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.parking = parking;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public boolean resigtVehicle(String plateNumber){
        
        return true;
    }

    public boolean resigtExitVehicle(String plateNumber){

        return true;
    }
}

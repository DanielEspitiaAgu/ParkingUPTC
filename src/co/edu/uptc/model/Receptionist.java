package co.edu.uptc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Receptionist extends User {
    private String name;
    private String lastName;
    private String nit;
    private String phone;
    private Parking parking;

    public Receptionist(String userName, String email, String password, String name, String lastName, String phone, Parking parking, String nit) {
        super(userName, email, password);
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.parking = parking;
        this.nit = nit;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
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

    public boolean registVehicle(String plateNumber){
        GateControl gate = parking.validateGateControl(this);
        return gate.addNewTicket(new Ticket(LocalDateTime.now(), plateNumber));
    }
    
    public boolean registExitVehicle(String plateNumber, double change){
        GateControl gate = parking.validateGateControl(this);
        return gate.completeTicket(plateNumber, change);
    }
    
    public double searchCost(String plateNumber){
        GateControl gate = parking.validateGateControl(this);
        try {
            return gate.consultIncompleteTicket(plateNumber).getCost();
        } catch (NullPointerException e) {
            return -1;
        }
    }
}

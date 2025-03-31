package co.edu.uptc.model;

import java.time.LocalDateTime;

public class Receptionist extends User {
    private String phone;
    private Parking parking;

    public Receptionist(String idNumber, String email, String password, String name, String lastName, String phone, Parking parking) {
        super(idNumber, email, password, name, lastName);
        this.phone = phone;
        this.parking = parking;
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

    public boolean registerVehicle(String plateNumber){
        GateControl gate = parking.validateGateControl(this);
        return gate.addNewTicket(new Ticket(LocalDateTime.now(), plateNumber));
    }
    
    public boolean registerExitVehicle(String plateNumber, double change){
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

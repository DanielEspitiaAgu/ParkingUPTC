package co.edu.uptc.model;
import java.time.LocalDateTime;
import java.util.ArrayList;
public class Ticket {
    private LocalDateTime entryDate;
    private LocalDateTime exitDate;
    private double cost;
    private double change;
    private String vehicleNumberPlate;
    private boolean isComplete;
    private Receptionist receptionist;

    public Ticket(LocalDateTime entryDate, String vehicleNumberPlate, Receptionist receptionist) {
        this.entryDate = entryDate;
        this.receptionist = receptionist;
        this.vehicleNumberPlate = vehicleNumberPlate;
        this.exitDate = null;
        this.cost = 0;
        this.isComplete = false;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDateTime getExitDate() {
        return exitDate;
    }

    public void setExitDate(LocalDateTime exitDate) {
        this.exitDate = exitDate;
    }

    public double getCost() {
        if (!isComplete) {
            cost = 2000 + Parking.minuteFee*Math.abs(entryDate.getMinute() - LocalDateTime.now().getMinute());
        }
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getVehicleNumberPlate() {
        return vehicleNumberPlate;
    }

    public void setVehicleNumberPlate(String vehiclePlate) {
        this.vehicleNumberPlate = vehiclePlate;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public void setExitAttributes(double change) {
        this.exitDate = LocalDateTime.now();
        this.change = change;
        this.isComplete = true;
        cost = 2000 + Parking.minuteFee*(entryDate.getMinute() - exitDate.getMinute());
    }

    public ArrayList<String> generateEntryTicket(String pakingName) {
        ArrayList<String> entryTicket = new ArrayList<String>();
        entryTicket.add(pakingName);
        entryTicket.add(entryDate.getDayOfMonth() + "/" + entryDate.getMonth() + "/" + entryDate.getYear());
        entryTicket.add(vehicleNumberPlate);
        entryTicket.add(entryDate.getHour() + ":" + entryDate.getMinute());
        return entryTicket;
    }

    public ArrayList<String> generateExitTicket(String parkingName) {
        ArrayList<String> exitTicket = new ArrayList<String>();
        exitTicket.add(parkingName);
        exitTicket.add(vehicleNumberPlate);
        exitTicket.add(entryDate.getDayOfMonth() + "/" + entryDate.getMonth() + "/" + entryDate.getYear());
        exitTicket.add(entryDate.getHour() + ":" + entryDate.getMinute());
        exitTicket.add(exitDate.getHour() + ":" + exitDate.getMinute());
        exitTicket.add(cost + "");
        exitTicket.add(change + "");
        exitTicket.add((change+cost)+" ");
        
        return exitTicket;
    }

    public Receptionist getReceptionist() {
        return receptionist;
    }

    public void setReceptionist(Receptionist receptionist) {
        this.receptionist = receptionist;
    }

    
}
package co.edu.uptc.model;
import java.util.ArrayList;

public class Admin extends User {
    private ParkingSystem parkingSystem;

    public Admin(String idNumber, String email, String password, ParkingSystem parkingSystem, String name, String lastName) {
        super(idNumber, email, password, name, lastName);
        this.parkingSystem = parkingSystem;
    }
    
    public ParkingSystem getParkingSystem() {
        return parkingSystem;
    }

    public void setParkingSystem(ParkingSystem parkingSystem) {
        this.parkingSystem = parkingSystem;
    }
    
    public boolean createParking(String name, String address, int capacity, ArrayList<String> scheduleList) {
        try {
            Parking newParking = new Parking(name, address, capacity,scheduleList);        
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean createReceptionist(String idNumber, String email, String password, String name, String lastName, String phone) {
        Receptionist newReceptionist = new Receptionist(idNumber, email, password, name, lastName, phone, parkingSystem.getParkings().get(0));
        return true;
    }
    
    public boolean editReceptionist(String document, String password) {
        for(User user:parkingSystem.getUsers()){
            if (user instanceof Receptionist) {            
                if(user.getIdNumber().equals(document)){
                    user.setPassword(password);
                    return true;
                }
            }
        }
        return false;
    }

}

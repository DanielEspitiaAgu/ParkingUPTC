package co.edu.uptc.model;
import java.util.ArrayList;

public class Admin extends User {
    
    private ParkingSystem parkingSystem;

    public Admin(String userName, String email, String password, ParkingSystem parkingSystem) {
        super(userName, email, password);
        this.parkingSystem = parkingSystem;
    }
    
    public boolean createParking(String name, String address, int capacity, ArrayList<String> scheduleList) {
        Parking newParking = new Parking(name, address, capacity,scheduleList);        
        return true;
    }

    public ParkingSystem getParkingSystem() {
        return parkingSystem;
    }

    public void setParkingSystem(ParkingSystem parkingSystem) {
        this.parkingSystem = parkingSystem;
    }

    public boolean createReceptionist(String userName, String email, String password, String name, String lastName, String phone) {
        Receptionist newReceptionist = new Receptionist(userName, email, password, name, lastName, phone, parkingSystem.getParkings().get(0), userName);
        return true;
    }
    
    public boolean editReceptionist(String document, String password) {
        for(User user:parkingSystem.getUsers()){
            if (user instanceof Receptionist) {            
                if(user.getUserName().equals(document)){
                    user.setPassword(password);
                    return true;
                }
            }
        }
        return false;
    }



}

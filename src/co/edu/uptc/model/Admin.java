package co.edu.uptc.model;
import java.util.ArrayList;

public class Admin extends User {
    
    private ParkingSystem parkingSystem;

    public Admin(String userName, String email, String password, ParkingSystem parkingSystem) {
        super(userName, email, password);
        this.parkingSystem = parkingSystem;
    }
    
    public boolean createParking(String name, String address, int capacity, int ocupedParks, ArrayList<String> schedule) {

        return true;
    }

    public ParkingSystem getParkingSystem() {
        return parkingSystem;
    }

    public void setParkingSystem(ParkingSystem parkingSystem) {
        this.parkingSystem = parkingSystem;
    }

    public boolean createReceptionist(String userName, String email, String password, String name, String lastName, String phone) {
        
        return true;
    }
    
    public boolean editReceptionist() {

        return true;
    }


}

package co.edu.uptc.model;
import java.util.ArrayList;
import java.time.LocalDate;

public class ParkingSystem {
    private ArrayList<User> users;
    private ArrayList<Parking> parkings;

    public ParkingSystem() {
        users = new ArrayList<User>();
        parkings = new ArrayList<Parking>();
        users.add(new Admin("admin", "admin@uptc.edu.co", "123abc", this));
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setParkings(ArrayList<Parking> parkings) {
        this.parkings = parkings;
    }

    public ArrayList<Parking> getParkings() {
        return parkings;
    }

    public User logIn(String userName, String password) {
        for(User user:users){
            if(user.getUserName().equals(userName.toLowerCase()) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    public ArrayList<ArrayList<String>> generateParkingReport(LocalDate date){

        return null;
    }

    public String generateReceptionistReport(LocalDate date){
        return null;
    }
}
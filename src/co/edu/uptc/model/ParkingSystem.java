package co.edu.uptc.model;
import java.util.ArrayList;
import java.time.LocalDate;

public class ParkingSystem {
    private ArrayList<User> users;
    private ArrayList<Parking> parkings;

    public ParkingSystem() {
        users = new ArrayList<User>();
        parkings = new ArrayList<Parking>();
        Admin admin = new Admin("admin", "admin@uptc.edu.co", "123abc", this);
        users.add(admin);
        ArrayList<String> schedule = new ArrayList<String>();
        schedule.add("Lunes-6:00-22:00");        
        schedule.add("Martes-6:00-22:00");
        schedule.add("Miercoles-6:00-22:00");
        schedule.add("Jueves-6:00-22:00");
        schedule.add("Viernes-6:00-22:00");
        schedule.add("Sabado-6:00-22:00");
        admin.createParking("UPTC Parking", "Calle 1, 123", 10, new ArrayList<String>());
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
        ArrayList<ArrayList<String>> reports = new ArrayList<ArrayList<String>>();
        for(Parking parking:parkings){
            ArrayList<String> parkingReport = new ArrayList<String>();
            parkingReport.add(parking.getName());
            double income = 0;
            int numCars = 0;
            for (GateControl gate:parking.getGates()){
                income += gate.calculateIncome(date);
                numCars += gate.calculateVehiclesIncome(date);
            }
            parkingReport.add(income+"");
            parkingReport.add(numCars+"");
        }
        return reports;
    }
    
    public ArrayList<ArrayList<String>> generateReceptionistReport(LocalDate date){
        ArrayList<ArrayList<String>> receptionistReports = new ArrayList<ArrayList<String>>();
        for(Parking parking:parkings){
            if (parking.getGates().size() > 0) {
                ArrayList<String> receptionistReport = new ArrayList<String>();
                for (GateControl gate:parking.getGates()){
                    receptionistReport.add(gate.getReceptionist().getName()+" "+gate.getReceptionist().getLastName());
                    receptionistReport.add(gate.calculateIncome(date)+"");
                    receptionistReport.add(gate.calculateVehiclesIncome(date)+"");
                }
                receptionistReports.add(receptionistReport);
            }
        }
        return receptionistReports;
    }
}

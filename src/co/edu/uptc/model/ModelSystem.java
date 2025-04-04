package co.edu.uptc.model;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ModelSystem {
    private ArrayList<Receptionist> receptionists;
    private ArrayList<Ticket> tickets;
    private Parking parking;
    private Admin admin;
    private User currentUser;

    public ModelSystem() {
        this.receptionists = new ArrayList<Receptionist>();
        this.parking = null;
        this.admin = new Admin("0001", "admin@uptc.edu.co", "123abc", "Pedrito", "Me electrocutaste");
        this.tickets = new ArrayList<Ticket>();
    }

    public String getUserName(){
        System.out.println(currentUser.getName()+" "+currentUser.getLastName());
        return currentUser.getName()+" "+currentUser.getLastName();
    }

    public String getParkingInfo(){
        return parking.toString();
    }

    public void setReceptionists(ArrayList<Receptionist> receptionists) {
        this.receptionists = receptionists;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void setreceptionists(ArrayList<Receptionist> receptionists) {
        this.receptionists = receptionists;
    }

    public ArrayList<Receptionist> getReceptionists() {
        return receptionists;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public Parking getParking() {
        return parking;
    }

    public int login(String idNumber, String password) {
        for(Receptionist receptionist:receptionists){
            if(receptionist.getIdNumber().equals(idNumber.toLowerCase()) && receptionist.getPassword().equals(password)){
                currentUser = receptionist;
                return 1;
                //return receptionist.getName()+" "+receptionist.getLastName();
            }
        }
        if(admin.getIdNumber().equals(idNumber.toLowerCase()) && admin.getPassword().equals(password)){
            currentUser = admin;
            return 2;
            //return admin.getName()+" "+admin.getLastName();
        }
        return -1;
    }

    public void logout(){
        currentUser = null;
    }

    public ArrayList<String> generateParkingReport(LocalDate date){
        ArrayList<String> reports = new ArrayList<String>();
        int numCars = 0;
        double totalIncome = 0;
        for(Ticket ticket:tickets){
            if(ticket.isComplete())
                if(ticket.getExitDate().getYear()==date.getYear() && ticket.getExitDate().getDayOfYear()==date.getDayOfYear()){
                    numCars++;
                    totalIncome+=ticket.getCost();
                }
        }
        reports.add(totalIncome+"");
        reports.add(numCars+"");
        return reports;
    }
    
    public ArrayList<ArrayList<String>> generateReceptionistReport(LocalDate date){
        ArrayList<ArrayList<String>> receptionistReports = new ArrayList<ArrayList<String>>();
        for(Receptionist receptionist:receptionists){
            ArrayList<String> receptionistReport = new ArrayList<String>();
            int numCars = 0;
            double totalIncome = 0;
            receptionistReport.add(receptionist.getName()+" "+receptionist.getLastName());
            for(Ticket ticket:tickets){
                if(ticket.getReceptionist()==receptionist){
                    if(ticket.isComplete()){
                        if(ticket.getExitDate().getYear()==date.getYear() && ticket.getExitDate().getDayOfYear()==date.getDayOfYear()){
                            numCars++;
                            totalIncome+=ticket.getCost();
                        }
                    }
                }
            }
            receptionistReport.add(totalIncome+"");
            receptionistReport.add(numCars+"");
            receptionistReports.add(receptionistReport);
        }
        return receptionistReports;
    }

    public boolean registerVehicle(String plateNumber){
        if(consultIncompleteTicket(plateNumber)==null){
            try{
                tickets.add(new Ticket(LocalDateTime.now(),plateNumber,(Receptionist)currentUser));
                return true;
            }catch (NullPointerException e){}
            catch (ClassCastException e){}
        }
        return false;
    }
    
    public boolean registerExitVehicle(String plateNumber, double change){
        Ticket ticket = consultIncompleteTicket(plateNumber);
        if(ticket!=null){
            ticket.setExitAttributes(change);
            return true;
        }
        return false;
    }
    
    public double searchCost(String plateNumber){
        try {
            return consultIncompleteTicket(plateNumber).getCost();
        } catch (NullPointerException e){
            return -1;
        }
    }

    private Ticket consultIncompleteTicket(String plateNumber) {
        for(Ticket ticket : tickets) {
            if(!ticket.isComplete()&&ticket.getVehicleNumberPlate().equals(plateNumber)){
                return ticket;
            }
        }
        return null;
    }

    public boolean createParking(String name, String address, int capacity, ArrayList<String> scheduleList) {
        if (parking==null) {
            try {
                parking = new Parking(name, address, capacity, scheduleList);
                return true;
            } catch (IllegalArgumentException e) {}
        }
        return false;
    }

    public boolean createReceptionist(String idNumber, String email, String password, String name, String lastName, String phone, String adress) {
        for(Receptionist receptionist:receptionists){
            if(receptionist.getIdNumber().equals(idNumber)){
                return false;
            }
        }
        receptionists.add(new Receptionist(idNumber, email, password, name, lastName, phone,adress));
        return true;
    }
    
    public boolean editReceptionist(String id, String document, String password) {
        for(Receptionist reseptionist:receptionists){
            if(reseptionist.getIdNumber().equals(id)){
                reseptionist.setPassword(password);
                reseptionist.setIdNumber(document);
                return true;
            }
        }
        
        return false;
    }

    public String getReceptionistInfo(String idNumber) {
        for(Receptionist receptionist:receptionists){
            if(receptionist.getIdNumber().equals(idNumber)){
                return receptionist.toString();
            }
        }
        return "";
    }
}

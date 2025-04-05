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
        this.parking = new Parking("Parking 1", "Calle 10 #45-67", 10, new ArrayList<String>());
        this.admin = new Admin("0001", "admin@uptc.edu.co", "123abc", "Pedrito", "Me electrocutaste");
        this.tickets = new ArrayList<Ticket>();

        receptionists.add(new Receptionist("001", "ana.gomez@example.com", "pass123", "Ana", "Gómez", "555-1234", "Calle 10 #45-67"));
        receptionists.add(new Receptionist("002", "mario.luna@example.com", "secure456", "Mario", "Luna", "555-5678", "Carrera 12 #34-89"));
        receptionists.add(new Receptionist("003", "lucia.mendez@example.com", "clave789", "Lucía", "Méndez", "555-8765", "Avenida Siempre Viva 123"));
        receptionists.add(new Receptionist("004", "carlos.rios@example.com", "admin321", "Carlos", "Ríos", "555-4321", "Diagonal 25 #98-54"));
        receptionists.add(new Receptionist("005", "sofia.vega@example.com", "pass987", "Sofía", "Vega", "555-6789", "Transversal 45 #76-32"));
    }

    public ArrayList<String> getReceptionistList(){
        ArrayList<String> receptionistList = new ArrayList<String>();
        for (Receptionist receptionist : receptionists){
            receptionistList.add(receptionist.getName()+" "+receptionist.getLastName()+":"+receptionist.getIdNumber());
        }
        return receptionistList;
    }

    public ArrayList<String> getEntryTicketInformation(String plate){
        for(Ticket ticket:tickets){
            if(ticket.getVehicleNumberPlate().equals(plate)){
                return ticket.generateEntryTicket(parking.getName());
            }
        }
        return null;
    }

    public int getFreeParkingSpaces(){
        if (parking!=null)
            return parking.getSpaces()-parking.getOcupedParks();
        return 0;
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
            }
        }
        if(admin.getIdNumber().equals(idNumber.toLowerCase()) && admin.getPassword().equals(password)){
            currentUser = admin;
            return 2;
        }
        return -1;
    }

    public void logout(){
        currentUser = null;
    }

    public String[] generateParkingReport(LocalDate date){
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
        return reports.toArray(new String[reports.size()]);
    }
    
    public String[][] generateReceptionistReport(LocalDate date){
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

        String[][] reports = new String[receptionistReports.size()][receptionistReports.get(0).size()];
        for(int i = 0; i < receptionistReports.size(); i++){
            reports[i] = receptionistReports.get(i).toArray(new String[receptionistReports.get(0).size()]);
        }
        return reports;
    }

    public boolean registerVehicle(String plateNumber){
        if(consultIncompleteTicket(plateNumber)==null){
            try{
                if (parking.getOcupedParks()==parking.getSpaces())
                    return false;
                tickets.add(new Ticket(LocalDateTime.now(),plateNumber,(Receptionist)currentUser));
                parking.setOcupedParks(parking.getOcupedParks()+1);
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
        if(parking==null)
            return false;
        for(Receptionist receptionist:receptionists){
            if(receptionist.getIdNumber().equals(idNumber)){
                return false;
            }
        }
        receptionists.add(new Receptionist(idNumber, email, password, name, lastName, phone,adress));
        return true;
    }
    
    public boolean editReceptionist(String id, String newId, String password) {
        for(Receptionist reseptionist:receptionists){
            if(reseptionist.getIdNumber().equals(id)){
                if(reseptionist.getPassword().equals(password)){
                    return false;
                }
                reseptionist.setPassword(password);
                reseptionist.setIdNumber(newId);
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

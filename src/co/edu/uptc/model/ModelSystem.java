package co.edu.uptc.model;
import java.util.ArrayList;
import java.util.Comparator;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ModelSystem {
    private ArrayList<Receptionist> receptionists;
    private ArrayList<Ticket> tickets;
    private Parking parking;
    private Admin admin;
    private User currentUser;
    private LocalDateTime activityStartTime;

    public ModelSystem() {
        this.receptionists = new ArrayList<Receptionist>();
        this.admin = new Admin("0001", "admin@uptc.edu.co", "123abc", "Jefe", "Administrativo");
        this.tickets = new ArrayList<Ticket>();
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
                activityStartTime = LocalDateTime.now();
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
            if(ticket.getEntryDate().getYear()==date.getYear() && ticket.getEntryDate().getDayOfYear()==date.getDayOfYear()){
                numCars++;
                if(ticket.isComplete())
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
                    if(ticket.getEntryDate().getYear()==date.getYear() && ticket.getEntryDate().getDayOfYear()==date.getDayOfYear()){
                        numCars++;
                        if(ticket.isComplete())
                            totalIncome+=ticket.getCost();
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
            if(reseptionist.getIdNumber().equals(newId))
                return false;
        }
        if(admin.getIdNumber().equals(newId))
            return false;
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

    public boolean completeTicket(String plateNumber, double recivedAmount){
        try{
            Ticket ticket = consultIncompleteTicket(plateNumber);
            double cost = ticket.getCost();
            if (cost>recivedAmount)
                return false;
            ticket.setExitAttributes(recivedAmount-cost);
            parking.setOcupedParks(parking.getOcupedParks()-1);
            return true;
        }catch (NullPointerException e){}
        return false;
    } 

    public Ticket consultUltimateVehicleTicket(String plate){
        ArrayList<Ticket> vehicleCompletedTickets = new ArrayList<Ticket>();
        for(Ticket ticket:tickets){
            if (ticket!=null) {
                if(ticket.getVehicleNumberPlate().equals(plate)&&ticket.isComplete()){
                    vehicleCompletedTickets.add(ticket);
                }    
            }
        }
        vehicleCompletedTickets.sort(Comparator.comparing(Ticket::getExitDate));
        return vehicleCompletedTickets.get(vehicleCompletedTickets.size()-1);
    }

    public double getTicketChange(String plate){
        Ticket recentTicket = consultUltimateVehicleTicket(plate);
        return recentTicket.getChange();
    }

    public ArrayList<String> generateExitTicket(String plate){
        Ticket recentTicket = consultUltimateVehicleTicket(plate);
        return recentTicket.generateExitTicket(parking.getName());
    }

    public ArrayList<String> generateCurrentReceptionistReport(){
        ArrayList<String> receptionistReport = new ArrayList<String>();
        Receptionist receptionist;
        receptionistReport.add(activityStartTime.getDayOfMonth()+"/"+activityStartTime.getMonthValue()+"/"+activityStartTime.getYear());
        receptionistReport.add(activityStartTime.getHour()+":"+activityStartTime.getMinute());
        receptionistReport.add(LocalTime.now().getHour()+":"+LocalTime.now().getMinute());
        Duration duration = Duration.between(activityStartTime, LocalDateTime.now());
        long hours = duration.toHours();
        long minutes = duration.toMinutes();
        receptionistReport.add(hours+" horas, "+minutes+ " minutos");
        if (currentUser!=null) {
            if (currentUser instanceof Receptionist) {
                receptionist = (Receptionist) currentUser;
                int vehicles = 0;
                double money = 0;
                for(Ticket ticket:tickets){
                    if (ticket!=null) {
                        if(ticket.getReceptionist()==receptionist){
                            vehicles++; 
                            if (ticket.isComplete()) {
                                money+=ticket.getCost();    
                            }
                        }    
                    }
                }
                receptionistReport.add(vehicles+"");
                receptionistReport.add(money+"");
                return receptionistReport;  
            }
        }
        return null;
    }

    
}

package co.edu.uptc.model;
import java.time.LocalDate;
import java.util.ArrayList;

public class GateControl {
    private Receptionist receptionist;
    private ArrayList<Ticket> tickets;

    public GateControl(Receptionist receptionist) {
        this.receptionist = receptionist;
        this.tickets = new ArrayList<Ticket>();
    }

    public Receptionist getReceptionist() {
        return receptionist;
    }

    public void setReceptionist(Receptionist receptionist) {
        this.receptionist = receptionist;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public boolean addNewTicket(Ticket ticket) {
        Ticket savedTicket = consultIncompleteTicket(ticket.getVehicleNumberPlate());
        if(savedTicket == null){
            tickets.add(ticket);
            return true;
        }
        return false;  
    }

    public boolean completeTicket(String plateNumber, double change) {
        Ticket ticket = consultIncompleteTicket(plateNumber);
        if(ticket == null)
            return false;
        ticket.setExitAttributes(change);
        return true;
    }

    public Ticket consultIncompleteTicket(String plateNumber) {
        for(Ticket ticket:tickets){
            if(ticket.getVehicleNumberPlate().equals(plateNumber) && !ticket.isComplete())
                return ticket;
        }
        return null;
    }

    public double calculateIncome(LocalDate date){
        double income = 0;
        for (Ticket ticket:tickets){
            if(ticket.isComplete()){
                if (ticket.getExitDate().getYear()==date.getYear() && ticket.getExitDate().getDayOfYear()==date.getDayOfYear()){
                    income += ticket.getCost();
                }
            }
        }
        return income;
    }
    
    public double calculateVehiclesIncome(LocalDate date){
        double numCars = 0;
        for (Ticket ticket:tickets){
            if (ticket.getEntryDate().getYear()==date.getYear() && ticket.getEntryDate().getDayOfYear()==date.getDayOfYear()){
                numCars++;
            }
            
        }
        return numCars;
    }

}
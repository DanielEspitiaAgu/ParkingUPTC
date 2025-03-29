package co.edu.uptc.model;
import java.util.ArrayList;

public class GateControl {
    private Receptionist receptionist;
    private ArrayList<Ticket> tickets;

    public GateControl(Receptionist receptionist, ArrayList<Ticket> tickets) {
        this.receptionist = receptionist;
        this.tickets = tickets;
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
}
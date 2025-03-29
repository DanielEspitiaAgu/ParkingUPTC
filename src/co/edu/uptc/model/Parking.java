package co.edu.uptc.model;

import java.util.ArrayList;

public class Parking {
    public static final int minuteFee = 50; 
    private String name;
    private String address;
    private int capacity;
    private int occupiedSpaces;
    private ArrayList<ServiceDaySchedule> schedule;
    private ArrayList<GateControl> gates;

    public Parking(String name, String address, int capacity, int occupiedSpaces, ArrayList<ServiceDaySchedule> schedule) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.occupiedSpaces = occupiedSpaces;
        this.schedule = schedule;
        gates = new ArrayList<GateControl>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getOcupedParks() {
        return occupiedSpaces;
    }

    public void setOcupedParks(int occupiedSpaces) {
        this.occupiedSpaces = occupiedSpaces;
    }

    public ArrayList<ServiceDaySchedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(ArrayList<ServiceDaySchedule> schedule) {
        this.schedule = schedule;
    }

    public ArrayList<GateControl> getGates() {
        return gates;
    }

    public void setGates(ArrayList<GateControl> gates) {
        this.gates = gates;
    }
    
}

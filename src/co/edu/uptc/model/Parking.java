package co.edu.uptc.model;

import java.time.LocalTime;
import java.util.ArrayList;

public class Parking {
    public static final int minuteFee = 50; 
    private String name;
    private String address;
    private int capacity;
    private int occupiedSpaces;
    private ArrayList<ServiceDaySchedule> schedule;
    private ArrayList<GateControl> gates;

    public Parking(String name, String address, int capacity, ArrayList<String> scheduleList) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        occupiedSpaces = 0;
        this.schedule = createServiceDaySchedules(scheduleList);
        this.gates = new ArrayList<GateControl>();
    }

    private ArrayList<ServiceDaySchedule> createServiceDaySchedules(ArrayList<String> scheduleList){
        ArrayList<ServiceDaySchedule> serviceDaySchedules= new ArrayList<>();
        for (int i = 0; i < scheduleList.size(); i++) {
            String[] aspects= scheduleList.get(i).split("-");
            serviceDaySchedules.add(new ServiceDaySchedule(Integer.parseInt(aspects[0]), LocalTime.parse(aspects[1]), LocalTime.parse(aspects[2])));
        }
        return serviceDaySchedules;
    }

    public GateControl validateGateControl(Receptionist receptionist){
        for(GateControl gate:gates){
            if(gate.getReceptionist().getUserName().equals(receptionist.getUserName())){
                return gate;
            }
        }
        gates.add(new GateControl(receptionist));
        return gates.get(gates.size()-1);
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

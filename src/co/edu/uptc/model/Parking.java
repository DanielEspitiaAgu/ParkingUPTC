package co.edu.uptc.model;

import java.time.LocalTime;
import java.util.ArrayList;

public class Parking {
    public static final int minuteFee = 50; 
    private String name;
    private String address;
    private int spaces;
    private int occupiedSpaces;
    private ArrayList<ServiceDaySchedule> schedule;

    public Parking(String name, String address, int spaces, ArrayList<String> scheduleList) {
        this.name = name;
        this.address = address;
        this.spaces = spaces;
        occupiedSpaces = 0;
        this.schedule = createServiceDaySchedules(scheduleList);
    }

    private ArrayList<ServiceDaySchedule> createServiceDaySchedules(ArrayList<String> scheduleList){
        ArrayList<ServiceDaySchedule> serviceDaySchedules= new ArrayList<>();
        for (int i = 0; i < scheduleList.size(); i++) {
            String[] aspects= scheduleList.get(i).split("-");
            int day = 0;
            switch (aspects[0]) {
                case "Lunes": day = 1; break;
                case "Martes": day = 2; break;
                case "Miercoles": day = 3; break;
                case "Jueves": day = 4; break;
                case "Viernes": day = 5; break; 
                case "Sabado": day = 6; break;
                case "Domingo": day = 7; break;
                case "Festivo": day = 8; break;
                default: throw new IllegalArgumentException("Invalid day");
            }
            serviceDaySchedules.add(new ServiceDaySchedule(Integer.parseInt(aspects[0]), LocalTime.parse(aspects[1]), LocalTime.parse(aspects[2])));
        }
        return serviceDaySchedules;
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

    public int getSpaces() {
        return spaces;
    }

    public void setSpaces(int capacity) {
        this.spaces = capacity;
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
}

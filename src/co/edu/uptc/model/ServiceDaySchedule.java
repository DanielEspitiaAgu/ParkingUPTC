package co.edu.uptc.model;

import java.time.LocalTime;

public class ServiceDaySchedule {
    private int day;
    private LocalTime startTime;
    private LocalTime finishTime;

    public ServiceDaySchedule(int day, LocalTime startTime, LocalTime finishTime) {
        this.day = day;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalTime finishTime) {
        this.finishTime = finishTime;
    }

    public String dayToString() {
        switch (day) {
            case 1:
                return "LUNES";
            case 2:
                return "MARTES";
            case 3:
                return "MIERCOLES";
            case 4:
                return "JUEVES";
            case 5:
                return "VIERNES";
            case 6:
                return "SABADO";
            case 7:
                return "DOMINGO";
            case 8:
                return "FESTIVO";
            default:
                return "Error";
        }
    }

    @Override
    public String toString() {
        return dayToString()+" (Apertura: "+startTime.toString()+", Cierre: "+finishTime.toString()+")\n";
    }
}

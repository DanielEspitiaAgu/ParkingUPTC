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

    @Override
    public String toString() {
        return day+"-"+startTime.toString()+"-"+finishTime.toString();
    }
}

package model;

import java.time.LocalTime;

public class StopPoint {

    private String street;
    private String suburb;
    private LocalTime time;


    StopPoint(String street, String suburb, LocalTime time) {
        this.street = street;
        this.suburb = suburb;
        this.time = time;
    }

    public StopPoint newInstance(StopPoint aStopPoint) {
        return new StopPoint(aStopPoint.street, aStopPoint.suburb, aStopPoint.time);
    }

    public void setTime(Object aTime){
        time = (LocalTime) aTime;
    }


    public void setStreet(String i){
        street = i;
    }

    @Override
    public String toString(){
        return street + " " + suburb + " " + time;
    }

    String getStreet() { return street; }

    String getSuburb() { return suburb; }

}

package model;

import java.time.LocalTime;

public class StopPoint {

    private String street;
    private String suburb;


    StopPoint(String street, String suburb) {
        this.street = street;
        this.suburb = suburb;
    }

    public StopPoint newInstance(StopPoint aStopPoint) {
        return new StopPoint(aStopPoint.street, aStopPoint.suburb);
    }



    public void setStreet(String i){
        street = i;
    }

    @Override
    public String toString(){
        return street + " " + suburb + " ";
    }

    String getStreet() { return street; }

    String getSuburb() { return suburb; }

}

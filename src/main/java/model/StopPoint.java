package model;

import javafx.collections.ObservableList;

import static javafx.collections.FXCollections.observableArrayList;

public class StopPoint {

    private Integer number;
    private String street;
    private String suburb;
    private ObservableList ridesInvolvedIn = observableArrayList();


    StopPoint(String street, String suburb , Integer number){
        this.street = street;
        this.suburb = suburb;
        this.number = number;
    }

    public void addRideRef(SharedRide ride){
        ridesInvolvedIn.add(ride);
    }

    public ObservableList getRideRefs(){
        return ridesInvolvedIn;
    }



    @Override
    public String toString(){
        return number + " " + street + " " + suburb + " ";
    }

    public Integer getNumber() { return number; }

    public String getStreet() { return street; }

    public String getSuburb() { return suburb; }

}

package model;

import javafx.collections.ObservableList;

import static javafx.collections.FXCollections.observableArrayList;

public class StopPoint {

    private String street;
    private String suburb;
    private ObservableList tripsInvolvedIn = observableArrayList();


    StopPoint(String street, String suburb) {
        this.street = street;
        this.suburb = suburb;
    }

    public void addTripRef(Trip trip){
        tripsInvolvedIn.add(trip);
    }



    @Override
    public String toString(){
        return street + " " + suburb + " ";
    }

    String getStreet() { return street; }

    String getSuburb() { return suburb; }

}

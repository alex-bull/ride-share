package model;

import javafx.collections.ObservableList;

import static javafx.collections.FXCollections.observableArrayList;

public class StopPoint {

    private String street;
    private String suburb;
    private ObservableList ridesInvolvedIn = observableArrayList();


    StopPoint(String street, String suburb) {
        this.street = street;
        this.suburb = suburb;
    }

    public void addRideRef(SharedRide ride){
        ridesInvolvedIn.add(ride);
    }

    public ObservableList getRideRefs(){
        return ridesInvolvedIn;
    }



    @Override
    public String toString(){
        return street + " " + suburb + " ";
    }

    public String getStreet() { return street; }

    public String getSuburb() { return suburb; }

}

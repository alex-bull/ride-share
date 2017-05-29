package model;

import javafx.collections.ObservableList;

import java.util.ArrayList;

import static javafx.collections.FXCollections.observableArrayList;

public class SharedRide {

    private Trip tripData;
    private ObservableList<ArrayList> route;
    private Integer availableSeats;
    private User driver;
    private ObservableList<User> usersOnRide = observableArrayList();


    SharedRide(Trip tripData, Integer availableSeats, User driver) {
        this.tripData = tripData;
        this.availableSeats = availableSeats;
        this.driver = driver;
        this.route = tripData.getRoute();
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public User getDriver(){ return driver; }

    public Trip getTripData(){ return tripData; }



    @Override
    public String toString() {
        return route.toString();
    }

    public void bookRide(User passenger) {
        availableSeats = availableSeats - 1;
        usersOnRide.add(passenger);
    }
}

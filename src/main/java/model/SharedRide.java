package model;

import javafx.collections.ObservableList;

import java.util.ArrayList;

import static javafx.collections.FXCollections.observableArrayList;

public class SharedRide {

    private ObservableList<ArrayList> route = observableArrayList();
    private Integer availableSeats;
    private User driver;
    private ObservableList<User> usersOnRide = observableArrayList();


    SharedRide(ObservableList<ArrayList> route, Integer availableSeats, User driver) {
        this.route = route;
        this.availableSeats = availableSeats;
        this.driver = driver;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public User getDriver(){ return driver; }

    @Override
    public String toString() {
        return route.toString();
    }

    public void bookRide(User passenger) {
        availableSeats = availableSeats - 1;
        usersOnRide.add(passenger);
    }
}

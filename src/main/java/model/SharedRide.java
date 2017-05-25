package model;

import javafx.collections.ObservableList;

import java.util.ArrayList;

import static javafx.collections.FXCollections.observableArrayList;

public class SharedRide {

    private ObservableList<ArrayList> route = observableArrayList();
    private Integer availableSeats;
    private User driver;


    SharedRide(ObservableList<ArrayList> route, Integer availableSeats, User driver) {
        this.route = route;
        this.availableSeats = availableSeats;
        this.driver = driver;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    @Override
    public String toString() {
        return route.toString();
    }

}

package model;

import javafx.collections.ObservableList;

import static javafx.collections.FXCollections.observableArrayList;

public class SharedRide {

    private ObservableList<StopPoint> route = observableArrayList();
    private Integer availableSeats;
    private User driver;


    SharedRide(ObservableList route, Integer availableSeats, User driver) {
        this.route = route;
        this.availableSeats = availableSeats;
        this.driver = driver;
    }



}

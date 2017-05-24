package model;

import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static javafx.collections.FXCollections.observableArrayList;
import static javafx.collections.FXCollections.observableHashMap;

public class Trip {

    private ObservableList<ArrayList> route = observableArrayList();
    private Boolean recurrent;
    private ArrayList<Boolean> days;
    private LocalDate date;
    private Integer direction;


    Trip(ObservableList<ArrayList> route, Boolean recurrent, ArrayList<Boolean> days, LocalDate date, Integer direction) {
        this.route = route;
        this.recurrent = recurrent;
        this.days = days;
        this.date = date;
        this.direction = direction;
    }


    public void setDays(ArrayList<Boolean> dayArray) {
        days = dayArray;
    }

    public void setRecurrent(Boolean status) {
        recurrent = status;
    }

    public void setDate(LocalDate aDate) {
        date = aDate;
    }

    public ObservableList<ArrayList> getRoute() {
        return route;
    }

    public void setRoute(ObservableList<ArrayList> routeArray) {
        route = routeArray;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }


    @Override
    public String toString() {
        return "route: " + route + " recurs?: " + recurrent + " days: " + days + " date: " + date + "direction" + direction;
    }

}

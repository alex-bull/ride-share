package model;

import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

import static javafx.collections.FXCollections.observableArrayList;

public class Trip {

    private ObservableList<StopPoint> route = observableArrayList();
    private Boolean recurrent;
    private ArrayList<Boolean> days;
    private LocalDate date;
    private Integer direction;

    Trip(ObservableList route, Boolean recurrent, ArrayList<Boolean> days, LocalDate date, Integer direction) {
        this.route = route;
        this.recurrent = recurrent;
        this.days = days;
        this.date = date;
        this.direction = direction;
    }

    public void setDays(ArrayList<Boolean> dayArray){
        days = dayArray;
    }

    public void setRoute(ObservableList<StopPoint> routeArray){
        route = routeArray;
    }

    public void setRecurrent(Boolean  status){
        recurrent = status;
    }

    public void setDate(LocalDate aDate){
        date = aDate;
    }

    public ObservableList<StopPoint> getRoute(){
        return route;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }


    @Override
    public String toString(){
        return "route: " + route + " recurs?: " + recurrent + " days: " + days + " date: " + date + "direction" + direction;
    }


}

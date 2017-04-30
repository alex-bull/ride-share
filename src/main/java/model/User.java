package model;

import javafx.collections.ObservableList;

import java.util.ArrayList;

import static javafx.collections.FXCollections.observableArrayList;
import static model.Database.getUserArrayList;
import static model.Database.getUserID;


public class User {

    private ObservableList<StopPoint> route = observableArrayList();
    public ObservableList<StopPoint> getRoute(){
        return route;
    }

    private ObservableList<ObservableList> routeArrayList = observableArrayList();
    public ObservableList<ObservableList> getRouteArrayList(){
        return routeArrayList;
    }

    private ObservableList<Car> carArrayList = observableArrayList();
    public ObservableList<Car> getCarArrayList(){
        return carArrayList;
    }

    private ObservableList<Trip> userTrips = observableArrayList();
    public ObservableList<Trip> getUserTrips(){
        return userTrips;
    }



    public void addToRoute(StopPoint stop){
        if(stop != null){
            StopPoint newStop = StopPoint.newInstance(stop);
            route.add(newStop);
            System.out.println(stop + "added");
        }
    }

    public void removeFromRoute(Object stop){
        if(stop != null){
            route.remove(stop);
            System.out.println(stop + "removed");
        }
    }

    public void clearRoute() {
        route.clear();
    }

    public void submitRoute() {
        ObservableList newRoute = observableArrayList();
        for (StopPoint item : route){
            newRoute.add(item);
        }
        routeArrayList.add(newRoute);
        System.out.println("Route array list is: " + routeArrayList);
    }

}

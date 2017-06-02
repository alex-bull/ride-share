package model;

import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static controllers.App.getDatabase;
import static javafx.collections.FXCollections.observableArrayList;


public class User {

    private Database database = getDatabase();
    private Integer UserID;
    private String UserName;
    private ObservableList<StopPoint> currentRoute = observableArrayList();
    private ObservableList<ObservableList> routeArrayList = observableArrayList();
    private ObservableList<Car> carArrayList = observableArrayList();
    private ObservableList<Trip> userTrips = observableArrayList();
    private Trip currentTrip;


    public User(Integer UserID, String Username) {
        this.UserID = UserID;
        this.UserName = Username;
    }


    public Integer getUserID() {
        return UserID;
    }

    public String getUserName() { return  UserName; }

    public ObservableList<StopPoint> getCurrentRoute() {
        return currentRoute;
    }

    public ObservableList<ObservableList> getRouteArrayList() {
        return routeArrayList;
    }

    public ObservableList<Car> getCarArrayList() {
        return carArrayList;
    }

    public ObservableList<Trip> getUserTrips() {
        return userTrips;
    }

    public ObservableList<ArrayList> getCurrentTripRoute() {
        return currentTrip.getRoute();
    }


    public void setCurrentTripRoute(ObservableList<StopPoint> routeToExpand) {
        ObservableList<ArrayList> expandedRoute = observableArrayList();
        for (StopPoint point : routeToExpand){
            expandedRoute.add(new ArrayList<>(Arrays.asList(point, LocalTime.MIN)));
        }
        currentTrip.setRoute(expandedRoute);
    }

    public boolean submitCar(String type, String model, String colour, String license, String year, Integer seatNum, ArrayList<TextField> carFieldArrayList) throws Exception {
        if (!Objects.equals(type, "") && !Objects.equals(model, "") && !Objects.equals(colour, "") && !Objects.equals(license, "") && !Objects.equals(year, "") && seatNum != null) {
            System.out.println(type.getClass() + model + colour);
            database.getUserHashMap().get(UserID).getCarArrayList().add(new Car(type, model, colour, license, year, seatNum));
            return true;
//            System.out.println("Car added");
//            System.out.println(carFieldArrayList.get(0).getText());
        } else {
            for (TextField field : carFieldArrayList) {
                if (field.getText().isEmpty()) {
                    field.setStyle("-fx-focus-color: red");
                    field.setStyle("-fx-border-color: red");
                } else {
                    field.setStyle("-fx-focus-color: transparent");
                    field.setStyle("-fx-border-color: transparent");
                }
            }
        }
        return false;
    }

    public void removeCar(int indexOfSelected) {
        try {
            getDatabase().getCurrentUser().getCarArrayList().remove(indexOfSelected);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
    }

    public void addToRoute(StopPoint stop, ObservableList<StopPoint> stopShallowCopy) {
        if (stop != null) {
            currentRoute.add(stop);
            stopShallowCopy.remove(stop);
            System.out.println(stop + "added");
//            System.out.println("route " + route + "               shallowcopy " + stopShallowCopy + "              original " + getDatabase().getStopPointArrayList());
        }
    }

    public void removeFromRoute(Object stop, ObservableList<StopPoint> stopShallowCopy) {
        if (stop != null) {
            currentRoute.remove(stop);
            stopShallowCopy.add((StopPoint) stop);
            System.out.println(stop + "removed");
//            System.out.println("route " + route + "               shallowcopy " + stopShallowCopy + "              original " + getDatabase().getStopPointArrayList());
        }
    }

    public void clearRoute() {
        currentRoute.clear();
    }

    public boolean submitRoute() {
        ObservableList newRoute = observableArrayList();
        for (StopPoint item : currentRoute) {
            newRoute.add(item);
        }
        if (!newRoute.isEmpty()){
            routeArrayList.add(newRoute);
            System.out.println("Route array list is: " + routeArrayList);
            return true;
        }
        return false;
    }

    public void updateRecurrentDays(Boolean Mon, Boolean Tue, Boolean Wed, Boolean Thu, Boolean Fri, Boolean Sat, Boolean Sun) {
        currentTrip.setDays(new ArrayList<>(Arrays.asList(Mon, Tue, Wed, Thu, Fri, Sat, Sun)));
    }

    public void updateRecurrentStatus(boolean status) {
        currentTrip.setRecurrent(status);
    }

    public boolean submitTrip() {
        if (currentTrip.getRoute() != null) {
            database.getCurrentUser().getUserTrips().add(currentTrip);
            System.out.println("User " + getUserID() + "'s Trip array list is: " + userTrips);
            return true;
        } else {
            return false;
        }
    }

    public void newCurrentTrip() {
        currentTrip = new Trip(null, false, new ArrayList<>(Arrays.asList(false, false, false, false, false, false, false)), null, 0);
    }

    public void updateTripDate(LocalDate aDate) {
        currentTrip.setDate(aDate);
    }

    public void updateTime(LocalTime aTime, ArrayList selectedStop) {
        selectedStop.set(1, aTime);
    }

    public void updateDirections(Boolean toUni) {
        if (toUni) {
            currentTrip.setDirection(0);
        } else {
            currentTrip.setDirection(1);
        }
    }

}

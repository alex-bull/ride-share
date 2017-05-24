package model;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static controllers.App.getPrimaryStage;
import static javafx.collections.FXCollections.observableArrayList;
import static javafx.fxml.FXMLLoader.getDefaultClassLoader;

/**
 * Created by abu59 on 4/04/17.
 */
public class Database{

    private ObservableList<StopPoint> stopPointArrayList = observableArrayList();
    public ObservableList<StopPoint> getStopPointArrayList() { return stopPointArrayList; }

    private HashMap<Integer, User> userHashMap = new HashMap<>();
    public void addUser(Integer ID){
        userHashMap.put(0, new User(ID));
    }
    public HashMap<Integer, User> getUserHashMap(){
        return userHashMap;
    }

    private int currentUser = 0;
    public int getUserID(){
        return userHashMap.get(currentUser).getUserID();
    }

    private Trip currentTrip = new Trip(null, null, null, null, 0);

    public ObservableList<StopPoint> getCurrentRoute(){
        return currentTrip.getRoute();
    }

    private ObservableList<LocalTime> times = observableArrayList(LocalTime.MIN);
    public ObservableList<LocalTime> getTimes(){
        return times;
    }

    private ObservableList<SharedRide> sharedRides = observableArrayList();

    public void populateTimes(){
        for (int i = 0; i<287; i++){
            times.add(times.get(i).plusMinutes(5));
        }

    }

    private boolean checkForDuplicates(ArrayList<TextField> pointFieldArrayList){
        for (StopPoint point : stopPointArrayList){
            if(point.getStreet().equals(pointFieldArrayList.get(0).getText()) && point.getSuburb().equals(pointFieldArrayList.get(1).getText())){
                return true;
            }
        }
        return false;
    }


    public void submitPoint(ArrayList<TextField> pointFieldArrayList) throws Exception {
        if((!pointFieldArrayList.get(0).getText().isEmpty()) && (!pointFieldArrayList.get(1).getText().isEmpty()) && (!checkForDuplicates(pointFieldArrayList))){
            stopPointArrayList.add(new StopPoint(pointFieldArrayList.get(0).getText(), pointFieldArrayList.get(1).getText(), null));
            System.out.println("Point added");
            System.out.println(pointFieldArrayList.get(0).getText());
            System.out.println(pointFieldArrayList.get(1).getText());

            Parent root = FXMLLoader.load(getDefaultClassLoader().getResource("driverTools.fxml"));
            Scene scene = new Scene(root);
            getPrimaryStage().setTitle("Welcome");
            getPrimaryStage().setScene(scene);
        } else {
            for (TextField field : pointFieldArrayList) {
                if (field.getText().isEmpty()) {
                    field.setStyle("-fx-focus-color: red");
                    field.setStyle("-fx-border-color: red");
                } else {
                    field.setStyle("-fx-focus-color: transparent");
                    field.setStyle("-fx-border-color: transparent");
                }
            }
        }
    }


    public void submitCar(ArrayList<TextField> carFieldArrayList) throws Exception {
        if((!carFieldArrayList.get(0).getText().isEmpty()) && (!carFieldArrayList.get(1).getText().isEmpty()) && (!carFieldArrayList.get(2).getText().isEmpty()) && (!carFieldArrayList.get(3).getText().isEmpty()) && (!carFieldArrayList.get(4).getText().isEmpty()) && (!carFieldArrayList.get(5).getText().isEmpty())){
            userHashMap.get(currentUser).getCarArrayList().add(new Car(carFieldArrayList.get(0).getText(), carFieldArrayList.get(1).getText(), carFieldArrayList.get(2).getText(), carFieldArrayList.get(3).getText(), carFieldArrayList.get(4).getText(), Integer.parseInt(carFieldArrayList.get(5).getText())));
            System.out.println("Car added");
            System.out.println(carFieldArrayList.get(0).getText());

            Parent root = FXMLLoader.load(getDefaultClassLoader().getResource("viewCars.fxml"));
            Scene scene = new Scene(root);
            getPrimaryStage().setTitle("Welcome");
            getPrimaryStage().setScene(scene);
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
    }


    public void removeCar(int indexOfSelected){
        userHashMap.get(currentUser).getCarArrayList().remove(indexOfSelected);
    }



    public void expandRoute(ObservableList<StopPoint> routeToExpand) {
        if(routeToExpand != null) {
            newCurrentTrip();
//            ObservableList<StopPoint> clonedRoute = observableArrayList();
//            for (StopPoint i : routeToExpand){
//                StopPoint clonedPoint = i.newInstance(i);
//                clonedRoute.add(clonedPoint);
//            }
            currentTrip.setRoute(routeToExpand);
        }
    }

    public void updateRecurrentDays(Boolean Mon, Boolean Tue, Boolean Wed, Boolean Thu, Boolean Fri, Boolean Sat, Boolean Sun){
        currentTrip.setDays(new ArrayList<>(Arrays.asList(Mon, Tue, Wed, Thu, Fri, Sat, Sun)));
    }

    public void updateRecurrentStatus(boolean status){
        currentTrip.setRecurrent(status);
    }

    public boolean submitTrip(){
        if (currentTrip.getRoute() != null) {
            getUserHashMap().get(getUserID()).getUserTrips().add(currentTrip);
            return true;
        } else {
            return false;
        }
    }

    public void newCurrentTrip(){
        currentTrip = new Trip(null, false, new ArrayList<>(Arrays.asList(false, false, false, false, false, false, false)), null, 0);
    }

    public void updateTripDate(LocalDate aDate){
        currentTrip.setDate(aDate);
    }

    public void updateTime(Object aTime, Object aStop) {
        StopPoint selectedStop = (StopPoint) aStop;
        selectedStop.setTime(aTime);
    }

    public void updateDirections(Boolean toUni){
        if (toUni == true){
            currentTrip.setDirection(0);
        } else {
            currentTrip.setDirection(1);
        }
    }

    public void submitSharedRide(Object route, Object seatNum) {
        sharedRides.add(new SharedRide(observableArrayList(route), (Integer) seatNum, getUserHashMap().get(getUserID())));
        System.out.println(sharedRides);
        System.out.println(sharedRides.get(0).toString());
    }
}

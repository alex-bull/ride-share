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
import java.util.Date;

import static controllers.Main.getPrimaryStage;
import static javafx.collections.FXCollections.observableArrayList;
import static javafx.fxml.FXMLLoader.getDefaultClassLoader;

/**
 * Created by abu59 on 4/04/17.
 */
public class Database{

    private static ObservableList<StopPoint> stopPointArrayList = observableArrayList();
    public static ObservableList<StopPoint> getStopPointArrayList() { return stopPointArrayList; }

    private static ObservableList<Trip> tripArrayList = observableArrayList();
    public static ObservableList<Trip> getTripArrayList() { return tripArrayList; }

    private static ArrayList<User> userArrayList = new ArrayList<>();
    public static void addUser(){
        userArrayList.add(new User());
    }
    public static ArrayList<User> getUserArrayList(){
        return userArrayList;
    }

    private static int userID = 0;
    public static int getUserID(){
        return userID;
    }

    private static Trip currentTrip = new Trip(null, null, null, null);

    public static ObservableList<StopPoint> getCurrentRoute(){
        return currentTrip.getRoute();
    }

    private static ObservableList<LocalTime> times = observableArrayList(LocalTime.MIN);
    public static ObservableList<LocalTime> getTimes(){
        return times;
    }

    public static void populateTimes(){
        for (int i = 0; i<287; i++){
            times.add(times.get(i).plusMinutes(5));
        }

    }

    private static boolean checkForDuplicates(ArrayList<TextField> pointFieldArrayList){
        for (StopPoint point : stopPointArrayList){
            if(point.getStreet().equals(pointFieldArrayList.get(0).getText()) && point.getSuburb().equals(pointFieldArrayList.get(1).getText())){
                return true;
            }
        }
        return false;
    }


    public static void submitPoint(ArrayList<TextField> pointFieldArrayList) throws Exception {
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


    public static void submitCar(ArrayList<TextField> carFieldArrayList) throws Exception {
        if((!carFieldArrayList.get(0).getText().isEmpty()) && (!carFieldArrayList.get(1).getText().isEmpty()) && (!carFieldArrayList.get(2).getText().isEmpty()) && (!carFieldArrayList.get(3).getText().isEmpty()) && (!carFieldArrayList.get(4).getText().isEmpty()) && (!carFieldArrayList.get(5).getText().isEmpty())){
            userArrayList.get(userID).getCarArrayList().add(new Car(carFieldArrayList.get(0).getText(), carFieldArrayList.get(1).getText(), carFieldArrayList.get(2).getText(), carFieldArrayList.get(3).getText(), carFieldArrayList.get(4).getText(), carFieldArrayList.get(5).getText()));
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


    public static void removeCar(int indexOfSelected){
        userArrayList.get(userID).getCarArrayList().remove(indexOfSelected);
    }



    public static void expandRoute(ObservableList<StopPoint> routeToExpand) {
        if(routeToExpand != null) {
            Database.newCurrentTrip();
            ObservableList<StopPoint> clonedRoute = observableArrayList();
            for (StopPoint i : routeToExpand){
                StopPoint clonedPoint = StopPoint.newInstance(i);
                clonedRoute.add(clonedPoint);
            }
            currentTrip.setRoute(clonedRoute);
        }
    }

    public static void updateRecurrentDays(Boolean Mon, Boolean Tue, Boolean Wed, Boolean Thu, Boolean Fri, Boolean Sat, Boolean Sun){
        currentTrip.setDays(new ArrayList<>(Arrays.asList(Mon, Tue, Wed, Thu, Fri, Sat, Sun)));
    }

    public static void updateRecurrentStatus(boolean status){
        currentTrip.setRecurrent(status);
    }

    public static boolean submitTrip(){
        if (currentTrip.getRoute() != null) {
            tripArrayList.add(currentTrip);
            getUserArrayList().get(getUserID()).getUserTrips().add(currentTrip);
            System.out.println(tripArrayList);
            return true;
        } else {
            return false;
        }
    }

    public static void newCurrentTrip(){
        currentTrip = new Trip(null, false, new ArrayList<>(Arrays.asList(false, false, false, false, false, false, false)), null);
    }

    public static void updateTripDate(LocalDate aDate){
        currentTrip.setDate(aDate);
    }

    public static void updateTime(Object aTime, Object aStop) {
        StopPoint selectedStop = (StopPoint) aStop;
        selectedStop.setTime(aTime);
    }
}

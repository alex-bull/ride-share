package model;

import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

import java.util.*;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * Created by abu59 on 4/04/17.
 */
public class Database{

    private ObservableList<StopPoint> stopPointArrayList = observableArrayList();
    public ObservableList<StopPoint> getStopPointArrayList() { return stopPointArrayList; }

    private HashMap<Integer, User> userHashMap = new HashMap<>();
    public void addUser(Integer ID, String Username){
        userHashMap.put(0, new User(ID, Username));
    }
    public HashMap<Integer, User> getUserHashMap(){
        return userHashMap;
    }

    private int currentUser = 0;
    public int getCurrentUserID(){
        return userHashMap.get(currentUser).getUserID();
    }
    public User getCurrentUser(){
        return userHashMap.get(currentUser);
    }

    private ObservableList<SharedRide> sharedRides = observableArrayList();



    private boolean checkForDuplicates(String number, String street, String suburb){
        for (StopPoint point : stopPointArrayList){
            if(point.getStreet().equals(street) && point.getSuburb().equals(suburb) && point.getNumber().equals(Integer.parseInt(number))){
                return true;
            }
        }
        return false;
    }


    public boolean submitPoint(String number, String street, String suburb, ArrayList<TextField> pointFieldArrayList) throws Exception {
        try {
            if ((!Objects.equals(number, "") && !Objects.equals(street, "") && !Objects.equals(suburb, "") && (!checkForDuplicates(number, street, suburb)))) {
                stopPointArrayList.add(new StopPoint(Integer.parseInt(number), street, suburb));
//            System.out.println("Point added");
//            System.out.println(pointFieldArrayList.get(0).getText());
//            System.out.println(pointFieldArrayList.get(1).getText());
//            System.out.println(pointFieldArrayList.get(2).getText());
                return true;
            } else {
                if (pointFieldArrayList != null) {
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
        } catch (NumberFormatException e){
            System.out.println(e.getMessage());
        }
        return false;
    }



    public void submitSharedRide(Trip trip, Integer seatNum) {
        ArrayList<ArrayList> tripRoute = new ArrayList<>(trip.getRoute());
        sharedRides.add(new SharedRide(trip, seatNum, getCurrentUser()));
        for (ArrayList<StopPoint> pointToAddRideRef : tripRoute ){
            pointToAddRideRef.get(0).addRideRef(sharedRides.get(sharedRides.size() - 1));
        }
        System.out.println(sharedRides);
        System.out.println(sharedRides.get(0).toString());
        System.out.println(stopPointArrayList.get(0).getRideRefs().toString());
    }
}

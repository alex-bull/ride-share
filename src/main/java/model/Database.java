package model;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
    public int getCurrentUserID(){
        return userHashMap.get(currentUser).getUserID();
    }
    public User getCurrentUser(){
        return userHashMap.get(currentUser);
    }

    private ObservableList<SharedRide> sharedRides = observableArrayList();



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
            stopPointArrayList.add(new StopPoint(pointFieldArrayList.get(0).getText(), pointFieldArrayList.get(1).getText()));
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



    public void submitSharedRide(Object route, Object seatNum) {
        ArrayList<ArrayList> tripRoute = new ArrayList(Arrays.asList(route));
        for (ArrayList pointToAddTripRef : tripRoute ){
            (pointToAddTripRef.get(0).
        }
        sharedRides.add(new SharedRide(observableArrayList(tripRoute), (Integer) seatNum, getCurrentUser()));
        System.out.println(sharedRides);
        System.out.println(sharedRides.get(0).toString());
    }
}

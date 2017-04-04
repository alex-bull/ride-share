package model;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static controllers.Main.getPrimaryStage;
import static javafx.collections.FXCollections.observableArrayList;
import static javafx.fxml.FXMLLoader.getDefaultClassLoader;

/**
 * Created by abu59 on 4/04/17.
 */
public class Database{

    private static ArrayList<User> userArrayList = new ArrayList<>();

    private static ObservableList<StopPoint> stopPointArrayList = observableArrayList();

    private static int userID = 0;


    public static int getUserID(){
        return userID;
    }


    public static ArrayList<User> getUserArrayList(){
        return userArrayList;
    }


    public static void addUser(){
        userArrayList.add(new User());
    }


    private static boolean checkForDuplicates(ArrayList<TextField> pointFieldArrayList){
        for (StopPoint point : stopPointArrayList){
            if(point.getStreet().equals(pointFieldArrayList.get(0).getText()) && point.getSuburb().equals(pointFieldArrayList.get(0).getText())){
                return true;
            }
        }
        return false;
    }


    public static void submitPoint(ArrayList<TextField> pointFieldArrayList) throws Exception {
        if((!pointFieldArrayList.get(0).getText().isEmpty()) && (!pointFieldArrayList.get(1).getText().isEmpty()) && (!checkForDuplicates(pointFieldArrayList))){
            stopPointArrayList.add(new StopPoint(pointFieldArrayList.get(0).getText(), pointFieldArrayList.get(1).getText()));
            System.out.println("Point added");
            System.out.println(pointFieldArrayList.get(0).getText());

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


}

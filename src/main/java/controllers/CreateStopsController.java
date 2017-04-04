package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import model.Database;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static controllers.Main.getPrimaryStage;
import static model.Database.submitPoint;


public class CreateStopsController implements Initializable{

    @FXML
    private TextField streetField;
    @FXML
    private TextField suburbField;

    private ArrayList<TextField> pointFieldArrayList = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pointFieldArrayList.add(streetField);
        pointFieldArrayList.add(suburbField);
    }


    @FXML
    private void loadDriverView() throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("driverTools.fxml"));
        Scene scene = new Scene(root);
        getPrimaryStage().setTitle("Welcome");
        getPrimaryStage().setScene(scene);
    }

    @FXML
    private void submitButtonPress() throws Exception {
        Database.submitPoint(pointFieldArrayList);
    }


}

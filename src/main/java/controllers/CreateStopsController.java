package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static controllers.App.getPrimaryStage;


public class CreateStopsController implements Initializable{

    @FXML
    private TextField streetNumberField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField suburbField;

    private ArrayList<TextField> pointFieldArrayList = new ArrayList<>();


    /**
     * Add FXML form field references into an array.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pointFieldArrayList.add(streetNumberField);
        pointFieldArrayList.add(streetField);
        pointFieldArrayList.add(suburbField);
    }


    /**
     * Changes the scene to the driver tools menu.
     * @throws Exception
     */
    @FXML
    private void loadDriverView() throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("driverTools.fxml"));
        Scene scene = new Scene(root);
        getPrimaryStage().setTitle("Welcome");
        getPrimaryStage().setScene(scene);
    }

    /**
     * Makes call to a method to submit a new point to the database when the submit button is pressed.
     * @throws Exception
     */
    @FXML
    private void submitButtonPress() throws Exception {
        if(App.getDatabase().submitPoint(streetNumberField.getText(), streetField.getText(), suburbField.getText(), pointFieldArrayList)){
            loadDriverView();
        }
    }


}

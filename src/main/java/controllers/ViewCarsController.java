package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import model.Database;

import java.net.URL;
import java.util.ResourceBundle;

import static controllers.Main.getPrimaryStage;
import static model.Database.getUserID;
import static model.Database.removeCar;


public class ViewCarsController implements Initializable {

    @FXML
    private ListView carListView;

    /**
     * Displays relavent elements in the car list view.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        carListView.setItems(Database.getUserArrayList().get(getUserID()).getCarArrayList());
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
     * Changes the scene to the register cars form/menu
     * @throws Exception
     */
    @FXML
    private void loadRegisterView() throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("registerCar.fxml"));
        Scene scene = new Scene(root);
        getPrimaryStage().setTitle("Welcome");
        getPrimaryStage().setScene(scene);
    }

    /**
     * Makes a call to a remove car from user method when the remove car button is pressed.
     */
    @FXML
    private void removeCarButtonPress(){
        removeCar(carListView.getSelectionModel().getSelectedIndex());
    }



}

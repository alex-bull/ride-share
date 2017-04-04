package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.net.URL;
import java.util.ResourceBundle;

import static controllers.Main.getPrimaryStage;


public class DriverController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void loadMainView() throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainMenu.fxml"));
        Scene scene = new Scene(root);
        getPrimaryStage().setTitle("Welcome");
        getPrimaryStage().setScene(scene);
    }

    @FXML
    private void loadViewCarsView() throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("viewCars.fxml"));
        Scene scene = new Scene(root);
        getPrimaryStage().setTitle("Your Cars");
        getPrimaryStage().setScene(scene);
    }

    @FXML
    private void loadCreateStopsView() throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("createStops.fxml"));
        Scene scene = new Scene(root);
        getPrimaryStage().setTitle("Submit a New Point");
        getPrimaryStage().setScene(scene);
    }

}

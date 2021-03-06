package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.net.URL;
import java.util.ResourceBundle;

import static controllers.App.getPrimaryStage;


public class DriverToolsController implements Initializable {

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

    @FXML
    private void loadCreateTripsView() throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("createTrips.fxml"));
        Scene scene = new Scene(root);
        getPrimaryStage().setTitle("Create a New Trip");
        getPrimaryStage().setScene(scene);
    }

    @FXML
    private void loadCreateRouteView() throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("createRoute.fxml"));
        Scene scene = new Scene(root);
        getPrimaryStage().setTitle("Create a New Route");
        getPrimaryStage().setScene(scene);
    }

    @FXML
    private void loadShareRideView() throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("shareRide.fxml"));
        Scene scene = new Scene(root);
        getPrimaryStage().setTitle("Share a Ride From Your Trips");
        getPrimaryStage().setScene(scene);
    }

}

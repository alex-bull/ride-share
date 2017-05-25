package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.net.URL;
import java.util.ResourceBundle;

import static controllers.App.getPrimaryStage;


public class PassengerToolsController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void loadBookRideView() throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("bookRide.fxml"));
        Scene scene = new Scene(root);
        getPrimaryStage().setTitle("Browse Stop Points");
        getPrimaryStage().setScene(scene);
    }

    @FXML
    private void loadMainMenuView() throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainMenu.fxml"));
        Scene scene = new Scene(root);
        getPrimaryStage().setTitle("Welcome");
        getPrimaryStage().setScene(scene);
    }
}

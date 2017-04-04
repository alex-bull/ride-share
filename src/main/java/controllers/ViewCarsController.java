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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        carListView.setItems(Database.getUserArrayList().get(getUserID()).getCarArrayList());
    }

    @FXML
    private void loadDriverView() throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("driverTools.fxml"));
        Scene scene = new Scene(root);
        getPrimaryStage().setTitle("Welcome");
        getPrimaryStage().setScene(scene);
    }

    @FXML
    private void loadRegisterView() throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("registerCar.fxml"));
        Scene scene = new Scene(root);
        getPrimaryStage().setTitle("Welcome");
        getPrimaryStage().setScene(scene);
    }

    @FXML
    private void removeCarButtonPress(){
        removeCar(carListView.getSelectionModel().getSelectedIndex());
    }



}

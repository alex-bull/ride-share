package controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import model.Database;
import model.StopPoint;
import model.User;
import model.StopPoint;

import java.net.URL;
import java.util.ResourceBundle;

import static controllers.Main.getPrimaryStage;
import static model.Database.getUserArrayList;
import static model.Database.getUserID;
import static model.Database.removeCar;


public class CreateRouteController implements Initializable {

    @FXML
    private ListView stopListView;

    @FXML
    private ListView routeListView;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stopListView.setItems(Database.getStopPointArrayList());
        routeListView.setItems(getUserArrayList().get(getUserID()).getRoute());
    }

    @FXML
    private void loadDriverView() throws Exception{
        getUserArrayList().get(getUserID()).clearRoute();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("driverTools.fxml"));
        Scene scene = new Scene(root);
        getPrimaryStage().setTitle("Welcome");
        getPrimaryStage().setScene(scene);
    }

    @FXML
    private void addStop(){
        Object selectedItem = stopListView.getSelectionModel().getSelectedItem();
        getUserArrayList().get(getUserID()).addToRoute((StopPoint) selectedItem);
        stopListView.getSelectionModel().clearSelection();
    }

    @FXML
    private void removeStop(){
        Object selectedItem = routeListView.getSelectionModel().getSelectedItem();
        getUserArrayList().get(getUserID()).removeFromRoute(selectedItem);
        routeListView.getSelectionModel().clearSelection();
    }

    @FXML
    private void submitRoute() throws Exception {
        getUserArrayList().get(getUserID()).submitRoute();
        loadDriverView();
    }

}

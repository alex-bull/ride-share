package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import model.Database;
import model.StopPoint;

import java.net.URL;
import java.util.ResourceBundle;

import static controllers.App.getDatabase;
import static controllers.App.getPrimaryStage;


public class CreateRouteController implements Initializable {

    @FXML
    private ListView stopListView;

    @FXML
    private ListView routeListView;

    private Database database;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        database = getDatabase();
        stopListView.setItems(database.getStopPointArrayList());
        routeListView.setItems(database.getUserHashMap().get(database.getUserID()).getRoute());
    }

    @FXML
    private void loadDriverView() throws Exception{
        database.getUserHashMap().get(database.getUserID()).clearRoute();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("driverTools.fxml"));
        Scene scene = new Scene(root);
        getPrimaryStage().setTitle("Welcome");
        getPrimaryStage().setScene(scene);
    }

    @FXML
    private void addStop(){
        Object selectedItem = stopListView.getSelectionModel().getSelectedItem();
        database.getUserHashMap().get(database.getUserID()).addToRoute((StopPoint) selectedItem);
        stopListView.getSelectionModel().clearSelection();
    }

    @FXML
    private void removeStop(){
        Object selectedItem = routeListView.getSelectionModel().getSelectedItem();
        database.getUserHashMap().get(database.getUserID()).removeFromRoute(selectedItem);
        routeListView.getSelectionModel().clearSelection();
    }

    @FXML
    private void submitRoute() throws Exception {
        database.getUserHashMap().get(database.getUserID()).submitRoute();
        loadDriverView();
    }

}

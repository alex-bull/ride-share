package controllers;

import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
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
import static javafx.collections.FXCollections.observableArrayList;


public class CreateRouteController implements Initializable {

    @FXML
    private ListView stopListView;

    @FXML
    private ListView routeListView;

    private Database database;

    private ObservableList<StopPoint> stopShallowCopy;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        database = getDatabase();
        stopShallowCopy = observableArrayList(database.getStopPointArrayList());
        stopListView.setItems(stopShallowCopy);
        routeListView.setItems(database.getCurrentUser().getRoute());
    }

    @FXML
    private void loadDriverView() throws Exception{
        database.getCurrentUser().clearRoute();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("driverTools.fxml"));
        Scene scene = new Scene(root);
        getPrimaryStage().setTitle("Welcome");
        getPrimaryStage().setScene(scene);
    }

    @FXML
    private void addStop(){
        Object selectedItem = stopListView.getSelectionModel().getSelectedItem();
        database.getCurrentUser().addToRoute((StopPoint) selectedItem, stopShallowCopy);
        stopListView.getSelectionModel().clearSelection();
    }

    @FXML
    private void removeStop(){
        Object selectedItem = routeListView.getSelectionModel().getSelectedItem();
        database.getCurrentUser().removeFromRoute(selectedItem, stopShallowCopy);
        routeListView.getSelectionModel().clearSelection();
    }

    @FXML
    private void submitRoute() throws Exception {
        database.getCurrentUser().submitRoute();
        loadDriverView();
    }

}

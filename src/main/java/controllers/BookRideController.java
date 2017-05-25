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

import java.net.URL;
import java.util.ResourceBundle;

import static controllers.App.getDatabase;
import static controllers.App.getPrimaryStage;
import static javafx.collections.FXCollections.observableArrayList;


public class BookRideController implements Initializable {

    @FXML
    private ListView stopListView;

    @FXML
    private ListView tripListView;

    private Database database = getDatabase();

    private ObservableList<StopPoint> stopShallowCopy;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stopListView.setItems(database.getStopPointArrayList());
        tripListView.getItems().clear();
    }

    @FXML
    private void loadPassengerView() throws Exception{
        database.getCurrentUser().clearRoute();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("passengerTools.fxml"));
        Scene scene = new Scene(root);
        getPrimaryStage().setTitle("Welcome");
        getPrimaryStage().setScene(scene);
    }

    @FXML
    private void pointClicked(){
        Object selectedItem = stopListView.getSelectionModel().getSelectedItem();
        tripListView.setItems(selectedItem);
        stopListView.getSelectionModel().clearSelection();
    }
//
//    @FXML
//    private void removeStop(){
//        Object selectedItem = routeListView.getSelectionModel().getSelectedItem();
//        database.getCurrentUser().removeFromRoute(selectedItem, stopShallowCopy);
//        routeListView.getSelectionModel().clearSelection();
//    }
//
//    @FXML
//    private void submitRoute() throws Exception {
//        database.getCurrentUser().submitRoute();
//        loadDriverView();
//    }

}

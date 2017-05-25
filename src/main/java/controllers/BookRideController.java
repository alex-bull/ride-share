package controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Database;
import model.SharedRide;
import model.StopPoint;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static controllers.App.getDatabase;
import static controllers.App.getPrimaryStage;
import static javafx.collections.FXCollections.observableArrayList;


public class BookRideController implements Initializable {

    @FXML
    private TableView<StopPoint> stopTableView;

    @FXML
    private ListView<SharedRide> rideListView;

    @FXML
    private TableColumn<StopPoint, String> streetColumn;

    @FXML
    private TableColumn<StopPoint, String> suburbColumn;

    private Database database = getDatabase();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stopTableView.setItems(database.getStopPointArrayList());
        streetColumn.setCellValueFactory(new PropertyValueFactory<>("street"));
        suburbColumn.setCellValueFactory(new PropertyValueFactory<>("suburb"));
        rideListView.getItems().clear();
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
        StopPoint selectedPoint = stopTableView.getSelectionModel().getSelectedItem();
        ObservableList<SharedRide> availableRides = observableArrayList();
        for (Object aRide: selectedPoint.getRideRefs()){
            if (((SharedRide) aRide).getAvailableSeats() > 0){
                availableRides.add((SharedRide) aRide);
            }
        }
        rideListView.setItems(availableRides);
        System.out.println(availableRides);
//        stopTableView.getSelectionModel().clearSelection();
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

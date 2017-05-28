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

import javax.swing.*;
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
    private TableColumn<StopPoint, String> streetNumberColumn;

    @FXML
    private TableColumn<StopPoint, String> streetColumn;

    @FXML
    private TableColumn<StopPoint, String> suburbColumn;

    private Database database = getDatabase();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stopTableView.setItems(database.getStopPointArrayList());
        streetNumberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
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
    }

    @FXML
    private void nextClicked() throws Exception {
        SharedRide selectedRide = rideListView.getSelectionModel().getSelectedItem();
        if (selectedRide != null) {
            int choice = JOptionPane.showConfirmDialog(null, "This ride will be provided by " + selectedRide.getDriver().getUserName() + ".\n" +
            "He will be driving a " + selectedRide.getDriver().getCarArrayList().get(0).getColour() + " " + selectedRide.getDriver().getCarArrayList().get(0).getYear() +
            " " + selectedRide.getDriver().getCarArrayList().get(0).getModel() + " with the license plate " + selectedRide.getDriver().getCarArrayList().get(0).getLicense() +
            ".\nThere are " + selectedRide.getAvailableSeats() + " available seat(s) remaining.\nWould you like to book this ride?", "Details/Confirm Booking", JOptionPane.YES_NO_OPTION);
            if (choice == 0){
                selectedRide.bookRide(database.getCurrentUser());
                loadPassengerView();
            }
        }
    }

}

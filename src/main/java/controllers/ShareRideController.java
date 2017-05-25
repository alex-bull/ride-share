package controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import model.Database;

import java.net.URL;
import java.util.ResourceBundle;

import static controllers.App.getPrimaryStage;
import static javafx.collections.FXCollections.observableArrayList;


public class ShareRideController implements Initializable {

    @FXML
    private ListView rideListView;

    @FXML
    private ComboBox seatsComboBox;

    private Database database;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        database = App.getDatabase();
        rideListView.setItems(database.getUserHashMap().get(database.getCurrentUserID()).getUserTrips());
        Integer seatNum = database.getCurrentUser().getCarArrayList().get(0).getSeatNum();
        ObservableList<Integer> nums = observableArrayList();
        for (int i = 1; i<=seatNum; i++){
            nums.add(i);
        }
        seatsComboBox.setItems(nums);
    }

    @FXML
    private void loadDriverView() throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("driverTools.fxml"));
        Scene scene = new Scene(root);
        getPrimaryStage().setTitle("Welcome");
        getPrimaryStage().setScene(scene);
    }

    @FXML
    private void shareRide() throws Exception {
        if(!seatsComboBox.getSelectionModel().isEmpty() && !rideListView.getSelectionModel().isEmpty()){
            database.submitSharedRide(rideListView.getSelectionModel().getSelectedItem(), seatsComboBox.getSelectionModel().getSelectedItem());
            loadDriverView();
        }
    }


}

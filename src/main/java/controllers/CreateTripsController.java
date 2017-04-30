package controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import model.Database;
import model.User;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static controllers.Main.getPrimaryStage;
import static model.Database.getUserArrayList;
import static model.Database.getUserID;


public class CreateTripsController implements Initializable {

    @FXML
    private ListView routeListView;

    @FXML
    private ListView expandedListView;

    @FXML
    private CheckBox monCheck;

    @FXML
    private CheckBox tueCheck;

    @FXML
    private CheckBox wedCheck;

    @FXML
    private CheckBox thuCheck;

    @FXML
    private CheckBox friCheck;

    @FXML
    private CheckBox satCheck;

    @FXML
    private CheckBox sunCheck;

    @FXML
    private CheckBox recurrentCheck;

    @FXML
    private DatePicker recurrenceDate;

    @FXML
    private ComboBox timesCombo;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Database.newCurrentTrip();
        routeListView.setItems(getUserArrayList().get(getUserID()).getRouteArrayList());
        expandedListView.setItems(Database.getCurrentRoute());
        timesCombo.setItems(Database.getTimes());
    }

    @FXML
    private void loadDriverView() throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("driverTools.fxml"));
        Scene scene = new Scene(root);
        getPrimaryStage().setTitle("Welcome");
        getPrimaryStage().setScene(scene);
    }


    @FXML
    private void expandSelectedRoute(){
        Object selectedItem = routeListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            Database.expandRoute((ObservableList) selectedItem);
            expandedListView.setItems(Database.getCurrentRoute());
            monCheck.setSelected(false);
            tueCheck.setSelected(false);
            wedCheck.setSelected(false);
            thuCheck.setSelected(false);
            friCheck.setSelected(false);
            satCheck.setSelected(false);
            sunCheck.setSelected(false);
            recurrentCheck.setSelected(false);
            recurrenceDate.setDisable(true);
        }
        routeListView.getSelectionModel().clearSelection();
    }

    @FXML
    private void submitTrip() throws IOException {
        if (!(recurrentCheck.isSelected() && recurrenceDate.getValue() == null)){
            if (Database.submitTrip()) {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("driverTools.fxml"));
                Scene scene = new Scene(root);
                getPrimaryStage().setTitle("Welcome");
                getPrimaryStage().setScene(scene);
            }
        }
    }

    @FXML
    private void updateDays(){
        Database.updateRecurrentDays(monCheck.isSelected(), tueCheck.isSelected(), wedCheck.isSelected(), thuCheck.isSelected(), friCheck.isSelected(), satCheck.isSelected(), sunCheck.isSelected());
    }

    @FXML
    private void updateRecurrent(){
        Database.updateRecurrentStatus(recurrentCheck.isSelected());
        updateDate();
        recurrenceDate.setDisable(!recurrenceDate.isDisabled());
    }

    @FXML
    private void updateDate(){
        Database.updateTripDate(recurrenceDate.getValue());
    }

    @FXML
    private void updateTime(){
        Database.updateTime(timesCombo.getSelectionModel().getSelectedItem(), expandedListView.getSelectionModel().getSelectedItem());
        expandedListView.refresh();
    }


}

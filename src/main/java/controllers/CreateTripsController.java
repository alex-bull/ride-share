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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static controllers.App.getPrimaryStage;


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

    @FXML
    private CheckBox toUniCheckBox;

    @FXML
    private CheckBox fromUniCheckBox;

    private Database database;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        database = App.getDatabase();
        database.newCurrentTrip();
        routeListView.setItems(database.getUserHashMap().get(database.getUserID()).getRouteArrayList());
        expandedListView.setItems(database.getCurrentRoute());
        timesCombo.setItems(database.getTimes());
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
            database.expandRoute((ObservableList) selectedItem);
            expandedListView.setItems(database.getCurrentRoute());
            monCheck.setSelected(false);
            tueCheck.setSelected(false);
            wedCheck.setSelected(false);
            thuCheck.setSelected(false);
            friCheck.setSelected(false);
            satCheck.setSelected(false);
            sunCheck.setSelected(false);
            recurrentCheck.setSelected(false);
            recurrenceDate.setDisable(true);
            toUniCheckBox.setSelected(true);
            fromUniCheckBox.setSelected(false);
            database.updateDirections(toUniCheckBox.isSelected());
        }
        routeListView.getSelectionModel().clearSelection();
    }

    @FXML
    private void submitTrip() throws IOException {
        if (!(recurrentCheck.isSelected() && recurrenceDate.getValue() == null)){
            if (database.submitTrip()) {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("driverTools.fxml"));
                Scene scene = new Scene(root);
                getPrimaryStage().setTitle("Welcome");
                getPrimaryStage().setScene(scene);
            }
        }
    }

    @FXML
    private void updateDays(){
        database.updateRecurrentDays(monCheck.isSelected(), tueCheck.isSelected(), wedCheck.isSelected(), thuCheck.isSelected(), friCheck.isSelected(), satCheck.isSelected(), sunCheck.isSelected());
    }

    @FXML
    private void updateRecurrent(){
        database.updateRecurrentStatus(recurrentCheck.isSelected());
        updateDate();
        recurrenceDate.setDisable(!recurrenceDate.isDisabled());
    }

    @FXML
    private void updateDate(){
        database.updateTripDate(recurrenceDate.getValue());
    }

    @FXML
    private void updateTime(){
        database.updateTime(timesCombo.getSelectionModel().getSelectedItem(), expandedListView.getSelectionModel().getSelectedItem());
        expandedListView.refresh();
    }

    @FXML
    private void toUniClicked(){
        fromUniCheckBox.setSelected(!toUniCheckBox.isSelected());
        database.updateDirections(toUniCheckBox.isSelected());
    }

    @FXML
    private void fromUniClicked(){
        toUniCheckBox.setSelected(!toUniCheckBox.isSelected());
        database.updateDirections(toUniCheckBox.isSelected());
    }



}

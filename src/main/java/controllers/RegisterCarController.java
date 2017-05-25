package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import model.Database;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static controllers.App.getDatabase;
import static controllers.App.getPrimaryStage;


public class RegisterCarController implements Initializable {

    @FXML
    private TextField typeField;
    @FXML
    private TextField modelField;
    @FXML
    private TextField colourField;
    @FXML
    private TextField licenseField;
    @FXML
    private TextField yearField;
    @FXML
    private TextField seatNumField;

    private ArrayList<TextField> carFieldArrayList = new ArrayList<>();

    private Database database;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        database = getDatabase();
        carFieldArrayList.add(typeField);
        carFieldArrayList.add(modelField);
        carFieldArrayList.add(colourField);
        carFieldArrayList.add(licenseField);
        carFieldArrayList.add(yearField);
        carFieldArrayList.add(seatNumField);
    }

    @FXML
    private void loadViewCarsView() throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("viewCars.fxml"));
        Scene scene = new Scene(root);
        getPrimaryStage().setTitle("Your Cars");
        getPrimaryStage().setScene(scene);
    }

    @FXML
    private void submitCarButtonPress() throws Exception {
        database.getCurrentUser().submitCar(carFieldArrayList);
    }
}

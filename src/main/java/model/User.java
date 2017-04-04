package model;

import javafx.collections.ObservableList;
import static javafx.collections.FXCollections.observableArrayList;


public class User {

    private ObservableList<Car> carArrayList = observableArrayList();

    public ObservableList<Car> getCarArrayList(){
        return carArrayList;
    }

}

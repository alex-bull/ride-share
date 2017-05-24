package model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.util.ArrayList;

import static controllers.App.getDatabase;
import static controllers.App.getPrimaryStage;
import static javafx.fxml.FXMLLoader.getDefaultClassLoader;

public class Car {

    private String type;
    private String model;
    private String colour;
    private String license;
    private String year;
    private Integer seatNum;

    Car(String type, String model, String colour, String license, String year, Integer seatNum) {
        this.type = type;
        this.model = model;
        this.colour = colour;
        this.license = license;
        this.year = year;
        this.seatNum = seatNum;
    }

    public Integer getSeatNum() {
        return seatNum;
    }

    @Override
    public String toString(){
        return model + " " + year;
    }


    //public double getLatitude() { return lat; }

    //public void setLatitude(double latitude) { this.lat = latitude; }

}

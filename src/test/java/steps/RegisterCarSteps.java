package steps;

import controllers.App;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import model.Car;
import model.Database;
import model.User;
import org.junit.Assert;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RegisterCarSteps {

    private Database database = new Database();

    private String type;
    private String model;
    private String colour;
    private String license;
    private String year;
    private Integer seatNum;

    public RegisterCarSteps() throws IOException {
        App.setDatabase(database);
        database.addUser(0, "Bob McRideshare");
    }

    @Given("^a car has a set of details$")
    public void a_car_has_a_set_of_details() throws Throwable {
        type = "Car";
        model = "Suzuki";
        colour = "White";
        license = "Toot";
        year = "1990";
        seatNum = 5;
    }

    @When("^details for a new car is submitted$")
    public void details_for_a_new_car_is_submitted() throws Throwable {
        database.getCurrentUser().submitCar(type, model, colour, license, year, seatNum, null);
    }

    @Then("^the car is registered to a user$")
    public void the_car_is_registered_to_a_user() throws Throwable {
        Car testCar = new Car("Car", "Suzuki", "White", "Toot", "1990", 5);
        Assert.assertEquals(database.getCurrentUser().getCarArrayList().get(0).getType(), testCar.getType());
        Assert.assertEquals(database.getCurrentUser().getCarArrayList().get(0).getModel(), testCar.getModel());
        Assert.assertEquals(database.getCurrentUser().getCarArrayList().get(0).getColour(), testCar.getColour());
        Assert.assertEquals(database.getCurrentUser().getCarArrayList().get(0).getLicense(), testCar.getLicense());
        Assert.assertEquals(database.getCurrentUser().getCarArrayList().get(0).getYear(), testCar.getYear());
        Assert.assertEquals(database.getCurrentUser().getCarArrayList().get(0).getSeatNum(), testCar.getSeatNum());
    }

    @When("^details for a new car is submitted 4 times$")
    public void details_for_a_new_car_is_submitted_4_times() throws Throwable {
        database.getCurrentUser().submitCar(type, model, colour, license, year, seatNum, null);
        database.getCurrentUser().submitCar("Truck", "Big Truck", "Red", "Br00m", "1990", 2, null);
        database.getCurrentUser().submitCar("Truck", "Small Truck", "Blue", "Br00m2", "1992", 2, null);
        database.getCurrentUser().submitCar("Truck", "Big Truck", "Red", "Br00m3", "1990", 2, null);
    }

    @Then("^four seperate vehicles are registered to the user and their car collection grows by 4$")
    public void a_new_car_is_registered_to_the_user_and_their_car_collection_grows_by_4() throws Throwable {
        Car testCar2 = new Car("Truck", "Big Truck", "Red", "Br00m", "1990", 2);
        Car testCar3 = new Car("Truck", "Small Truck", "Blue", "Br00m2", "1992", 2);

        Assert.assertEquals(database.getCurrentUser().getCarArrayList().size(), 4);

        Assert.assertEquals(database.getCurrentUser().getCarArrayList().get(1).getType(), testCar2.getType());
        Assert.assertEquals(database.getCurrentUser().getCarArrayList().get(1).getModel(), testCar2.getModel());
        Assert.assertEquals(database.getCurrentUser().getCarArrayList().get(1).getColour(), testCar2.getColour());
        Assert.assertEquals(database.getCurrentUser().getCarArrayList().get(1).getLicense(), testCar2.getLicense());
        Assert.assertEquals(database.getCurrentUser().getCarArrayList().get(1).getYear(), testCar2.getYear());
        Assert.assertEquals(database.getCurrentUser().getCarArrayList().get(1).getSeatNum(), testCar2.getSeatNum());

        Assert.assertEquals(database.getCurrentUser().getCarArrayList().get(2).getType(), testCar3.getType());
        Assert.assertEquals(database.getCurrentUser().getCarArrayList().get(2).getModel(), testCar3.getModel());
        Assert.assertEquals(database.getCurrentUser().getCarArrayList().get(2).getColour(), testCar3.getColour());
        Assert.assertEquals(database.getCurrentUser().getCarArrayList().get(2).getLicense(), testCar3.getLicense());
        Assert.assertEquals(database.getCurrentUser().getCarArrayList().get(2).getYear(), testCar3.getYear());
        Assert.assertEquals(database.getCurrentUser().getCarArrayList().get(2).getSeatNum(), testCar3.getSeatNum());
    }

}

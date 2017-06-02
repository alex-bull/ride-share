package steps;

import controllers.App;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import model.Car;
import model.Database;
import model.StopPoint;
import org.junit.Assert;

import java.io.IOException;

public class CreateStopPointSteps {

    private Database database = new Database();

    private String number;
    private String street;
    private String suburb;

    public CreateStopPointSteps() throws IOException {
        App.setDatabase(database);
        database.addUser(0, "Bob McRideshare");
    }

    @Given("^a stop point has valid details$")
    public void a_stop_point_has_valid_details() throws Throwable {
        this.number = "12";
        this.street = "Cat Street";
        this.suburb = "Meowbrook";
    }

    @When("^details for the new point is submitted and does not already exist$")
    public void details_for_the_new_point_is_submitted_and_does_not_already_exist() throws Throwable {
        database.submitPoint(number, street, suburb, null);
    }

    @Then("^the stop point is added to the database$")
    public void the_stop_point_is_added_to_the_database() throws Throwable {
        StopPoint testPoint = new StopPoint(12, "Cat Street", "Meowbrook");
        Assert.assertEquals(database.getStopPointArrayList().get(0).getNumber(), testPoint.getNumber());
        Assert.assertEquals(database.getStopPointArrayList().get(0).getStreet(), testPoint.getStreet());
        Assert.assertEquals(database.getStopPointArrayList().get(0).getSuburb(), testPoint.getSuburb());
    }

    @When("^details for the new point is submitted and already exists$")
    public void detailsForTheNewPointIsSubmittedAndAlreadyExists() throws Throwable {
        database.submitPoint(number, street, suburb, null);
        database.submitPoint(number, street, suburb, null);
    }

    @Then("^the stop point is not added to the database$")
    public void theStopPointIsNotAddedToTheDatabase() throws Throwable {
        Assert.assertTrue(database.getStopPointArrayList().size() == 1);
    }
}

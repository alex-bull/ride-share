Feature: Create a stop point

    Scenario: A new stop point is created and added to a collection of stop points in the database
        Given a stop point has valid details
        When details for the new point is submitted and does not already exist
        Then the stop point is added to the database

    Scenario: A new stop point is created and but already exists in the database
        Given a stop point has valid details
        When details for the new point is submitted and already exists
        Then the stop point is not added to the database